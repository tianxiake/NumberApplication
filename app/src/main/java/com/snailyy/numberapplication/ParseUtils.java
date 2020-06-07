package com.snailyy.numberapplication;

public class ParseUtils {


    public static int parseInt(String intValue) {
        return parseInt(intValue, -1);
    }


    public static int parseInt(String intValue, int defValue) {
        try {
            return Integer.parseInt(intValue);
        } catch (NumberFormatException e) {
        }
        return defValue;
    }

    public static float parseFloat(String floatValue) {
        return parseFloat(floatValue, -1f);
    }


    public static float parseFloat(String floatValue, float defValue) {
        try {
            return Float.parseFloat(floatValue);
        } catch (NumberFormatException e) {
        }
        return defValue;
    }
}

