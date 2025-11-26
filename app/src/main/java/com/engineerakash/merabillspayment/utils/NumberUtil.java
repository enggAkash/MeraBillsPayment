package com.engineerakash.merabillspayment.utils;

import java.text.DecimalFormat;

public class NumberUtil {

    public static String upToFixDecimalIfDecimalValueIsZero(double value) {
        return upToFixDecimalIfDecimalValueIsZero(value, 2);
    }

    public static String upToFixDecimalIfDecimalValueIsZero(double value, int decimalPoints) {
        int tempDecimalPoints = decimalPoints;

        if (tempDecimalPoints < 1) {
            tempDecimalPoints = 1;
            return String.valueOf((int) value);
        }

        StringBuilder decimalPointStr = new StringBuilder();
        for (int i = 0; i < tempDecimalPoints; i++) {
            decimalPointStr.append("#");
        }

        DecimalFormat df = new DecimalFormat("#." + decimalPointStr.toString());
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(tempDecimalPoints);
        return df.format(value);
    }

}
