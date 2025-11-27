package com.engineerakash.merabillspayment.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.engineerakash.merabillspayment.R;
import com.engineerakash.merabillspayment.data.pojo.Payment;
import com.engineerakash.merabillspayment.utils.NumberUtil;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private final ArrayList<Payment> paymentList;
    private final PaymentAdapterListener paymentAdapterListener;

    PaymentAdapter(ArrayList<Payment> paymentList, PaymentAdapterListener paymentAdapterListener) {
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

    public void removePayment(int position) {
        paymentList.remove(position);
        notifyItemRemoved(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addPayment(Payment payments) {
        paymentList.add(payments);
        notifyItemInserted(paymentList.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Payment> payments) {
        paymentList.clear();
        paymentList.addAll(payments);
        notifyDataSetChanged();
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public double getTotalAmount() {
        double totalAmount = 0.0;
        for (int i = 0; i < paymentList.size(); i++) {
            totalAmount += paymentList.get(i).getAmount();
        }

        return totalAmount;
    }

    public boolean hasPaymentType(String paymentMode) {
        for (int i = 0; i < paymentList.size(); i++) {
            if (paymentList.get(i).getPaymentMode().getData().equalsIgnoreCase(paymentMode)) {
                return true;
            }
        }
        return false;
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        Chip chip;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removePayment(getBindingAdapterPosition());
                    paymentAdapterListener.onRemovePaymentClicked(getBindingAdapterPosition());
                }
            });
        }

        public void bind(int position) {
            Payment payment = paymentList.get(position);

            String formattedAmount = NumberUtil.upToFixDecimalIfDecimalValueIsZero(payment.getAmount());
            String paymentDescription = chip.getContext().getString(R.string.payment_description, payment.getPaymentMode().getData(), formattedAmount);

            chip.setText(paymentDescription);
        }
    }


}
