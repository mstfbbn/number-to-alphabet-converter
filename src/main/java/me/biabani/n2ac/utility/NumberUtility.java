package me.biabani.n2ac.utility;

public final class NumberUtility {

    public static String removeLeadingZerosFromNumber(String number) {
        if (StringUtility.isNullOrEmpty(number)) {
            return number;
        }
        var stringBuilder = new StringBuilder(number);
        while (stringBuilder.charAt(0) == '0') {
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }
}
