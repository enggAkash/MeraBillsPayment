package com.engineerakash.merabillspayment.ui;

public interface AddPaymentListener {

    void onPaymentAdd(double amount, String paymentType, String provider, String reference);
}
