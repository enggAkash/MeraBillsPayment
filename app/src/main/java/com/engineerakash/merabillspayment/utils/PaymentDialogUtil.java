package com.engineerakash.merabillspayment.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.engineerakash.merabillspayment.R;
import com.engineerakash.merabillspayment.data.pojo.PaymentMode;
import com.engineerakash.merabillspayment.ui.AddPaymentListener;

import java.util.ArrayList;
import java.util.List;

public class PaymentDialogUtil {

    public static void openAddPaymentDialog(Context context, AddPaymentListener listener, List<String> remainingPaymentTypeList) {
        View addDialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_add_payment, null);

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(addDialogLayout)
                .show();

        EditText amountEt = (EditText) addDialogLayout.findViewById(R.id.amountEt);
        EditText providerEt = (EditText) addDialogLayout.findViewById(R.id.providerEt);
        EditText transactionReferenceEt = (EditText) addDialogLayout.findViewById(R.id.transactionReferenceEt);
        Spinner paymentTypeSpinner = (Spinner) addDialogLayout.findViewById(R.id.paymentTypeSpinner);
        TextView spinnerError = (TextView) addDialogLayout.findViewById(R.id.spinnerError);
        TextView cancelBtn = (TextView) addDialogLayout.findViewById(R.id.cancelDialogBtn);
        TextView oKBtn = (TextView) addDialogLayout.findViewById(R.id.oKBtn);

        ArrayList<String> modifiedRemainingPaymentOptions = new ArrayList<String>();
        modifiedRemainingPaymentOptions.add("Select payment option");
        modifiedRemainingPaymentOptions.addAll(remainingPaymentTypeList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                modifiedRemainingPaymentOptions

        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        paymentTypeSpinner.setAdapter(adapter);

        paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerError.setVisibility(View.GONE);

                if (position == 0) {
                    //don't handle select payment option
                    return;
                }

                if (modifiedRemainingPaymentOptions.get(position).equalsIgnoreCase(PaymentMode.CASH.getData())) {
                    providerEt.setVisibility(View.GONE);
                    transactionReferenceEt.setVisibility(View.GONE);
                } else {
                    providerEt.setVisibility(View.VISIBLE);
                    transactionReferenceEt.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                providerEt.setVisibility(View.GONE);
                transactionReferenceEt.setVisibility(View.GONE);
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        oKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amountEt.getText().isEmpty()) {
                    amountEt.setError("Please enter a valid amount");
                    return;
                }

                if (paymentTypeSpinner.getSelectedItemPosition() == AdapterView.INVALID_POSITION ||
                        paymentTypeSpinner.getSelectedItemPosition() == 0) {
                    spinnerError.setVisibility(View.VISIBLE);
                    return;
                }

                String provider;
                String reference;
                if (modifiedRemainingPaymentOptions.get(paymentTypeSpinner.getSelectedItemPosition()).equals(PaymentMode.CASH.getData())) {
                    provider = "";
                    reference = "";
                } else {
                    provider = providerEt.getText().toString();
                    reference = transactionReferenceEt.getText().toString();
                }

                try {
                    listener.onPaymentAdd(Double.parseDouble(
                                    amountEt.getText().toString()),
                            paymentTypeSpinner.getSelectedItem().toString(),
                            provider,
                            reference
                    );
                } catch (NumberFormatException | NullPointerException e) {
                    e.printStackTrace();
                }

                alertDialog.dismiss();
            }
        });


    }

}
