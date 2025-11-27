package com.engineerakash.merabillspayment.utils;

import static com.engineerakash.merabillspayment.data.pojo.PaymentMode.BANK_TRANSFER;
import static com.engineerakash.merabillspayment.data.pojo.PaymentMode.CASH;
import static com.engineerakash.merabillspayment.data.pojo.PaymentMode.CREDIT_CARD;

import com.engineerakash.merabillspayment.data.pojo.PaymentMode;
import com.engineerakash.merabillspayment.ui.PaymentAdapter;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    /**
     * Gets the list of payment methods that haven't been used yet.
     *
     * @param paymentAdapter The adapter containing current payments
     * @return List of remaining payment method names
     */
    public static List<String> getRemainingPaymentMethod(PaymentAdapter paymentAdapter) {
        ArrayList<String> remainingPaymentModes = new ArrayList<>();
        if (!paymentAdapter.hasPaymentType(CASH.getData())) {
            remainingPaymentModes.add(CASH.getData());
        }

        if (!paymentAdapter.hasPaymentType(BANK_TRANSFER.getData())) {
            remainingPaymentModes.add(BANK_TRANSFER.getData());
        }
        if (!paymentAdapter.hasPaymentType(CREDIT_CARD.getData())) {
            remainingPaymentModes.add(CREDIT_CARD.getData());
        }

        return remainingPaymentModes;
    }

    public static PaymentMode getPaymentModeFromString(String paymentType) {
        switch (paymentType) {
            case "Cash":
                return CASH;
            case "Bank Transfer":
                return BANK_TRANSFER;
            case "Credit Card":
                return CREDIT_CARD;
            default:
                return null;
        }
    }
}
