package com.engineerakash.merabillspayment.data.pojo;

public enum PaymentMode {
    CASH("Cash"), BANK_TRANSFER("Bank Transfer"), CREDIT_CARD("Credit Card");

    private final String data;
    PaymentMode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
