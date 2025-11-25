package com.engineerakash.merabillspayment.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineerakash.merabillspayment.R;
import com.engineerakash.merabillspayment.data.pojo.Payment;
import com.google.android.material.chip.Chip;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private final List<Payment> paymentList;
    private final PaymentAdapterListener paymentAdapterListener;

    PaymentAdapter(List<Payment> paymentList, PaymentAdapterListener paymentAdapterListener) {
        this.paymentList = paymentList;
        this.paymentAdapterListener = paymentAdapterListener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, parent, false);
        return new PaymentViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    private void closeIconClicked(int position) {
        notifyItemRemoved(position);
        paymentAdapterListener.onRemovePaymentClicked(position);
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        Chip chip;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeIconClicked(getBindingAdapterPosition());
                }
            });
        }

        public void bind(int position) {
            Payment payment = paymentList.get(position);

            String paymentDescription = chip.getContext().getString(R.string.payment_description, payment.getPaymentMode().getPaymentMode(), payment.getAmount());

            chip.setText(paymentDescription);
        }
    }


}
