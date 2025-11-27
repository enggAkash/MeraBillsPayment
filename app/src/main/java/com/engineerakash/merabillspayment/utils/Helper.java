package com.engineerakash.merabillspayment.utils;

import static com.engineerakash.merabillspayment.data.pojo.PaymentMode.BANK_TRANSFER;
import static com.engineerakash.merabillspayment.data.pojo.PaymentMode.CASH;
import static com.engineerakash.merabillspayment.data.pojo.PaymentMode.CREDIT_CARD;

import com.engineerakash.merabillspayment.ui.PaymentAdapter;

import java.util.ArrayList;
import java.util.List;

public class Helper {

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
}
