package com.engineerakash.merabillspayment.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.engineerakash.merabillspayment.R;
import com.engineerakash.merabillspayment.utils.FileHelper;
import com.engineerakash.merabillspayment.utils.NumberUtil;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private TextView totalAmount;
    private TextView addPaymentCta;
    private TextView saveBtn;
    private RecyclerView paymentsRv;

    private FileHelper fileHelper;

    private final PaymentAdapter paymentAdapter = new PaymentAdapter(Collections.emptyList(), position -> updateTotalAmount());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        init();
    }

    private void init() {
        initViews();

        fileHelper = new FileHelper(this);
        paymentAdapter.setList(fileHelper.getPayments());
        paymentsRv.setAdapter(paymentAdapter);

        initListeners();
    }

    private void initViews() {
        totalAmount = (TextView) findViewById(R.id.totalAmount);
        addPaymentCta = (TextView) findViewById(R.id.addPaymentCta);
        saveBtn = (TextView) findViewById(R.id.saveBtn);
        paymentsRv = (RecyclerView) findViewById(R.id.paymentsRv);
    }

    private void initListeners() {
        updateTotalAmount();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                try {
                    fileHelper.savePayments(paymentAdapter.getPaymentList());
                    message = getString(R.string.payments_saved_successfully);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    message = getString(R.string.failed_to_save_payments);
                }

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        addPaymentCta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateTotalAmount() {
        String formattedValue = NumberUtil.upToFixDecimalIfDecimalValueIsZero(paymentAdapter.getTotalAmount());

        totalAmount.setText(getString(R.string.total_amount_rs_1000, formattedValue));
    }
}