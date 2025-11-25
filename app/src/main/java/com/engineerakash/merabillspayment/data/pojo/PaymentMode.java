package com.engineerakash.merabillspayment.data.pojo;

public enum PaymentMode {
    CASH("Cash"), BANK_TRANSFER("Bank Transfer"), CREDIT_CARD("Credit Card");

    private final String paymentMode;
    PaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }
}
