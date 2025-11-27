package com.engineerakash.merabillspayment.data.pojo;

public class Payment {
    double amount;
    PaymentMode paymentMode;

    String provider;

    String transactionReference;

    public Payment(double amount, PaymentMode paymentMode) {
        this(amount, paymentMode, "", "");
    }

    public Payment(double amount, PaymentMode paymentMode, String provider, String transactionReference) {
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.provider = provider;
        this.transactionReference = transactionReference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }
}