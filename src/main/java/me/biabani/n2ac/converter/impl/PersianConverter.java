package me.biabani.n2ac.converter.impl;

import me.biabani.n2ac.converter.Converter;
import me.biabani.n2ac.utility.NumberUtility;
import me.biabani.n2ac.utility.StringUtility;

import java.util.Arrays;
import java.util.List;

public class PersianConverter implements Converter {

    private static final String AND = " و ";
    private static final List<String> number_places = Arrays.asList("", "هزار", "میلیون", "میلیارد", "بیلیون", "تریلیون", "کوادریلیون");
    private static final List<String> ones = Arrays.asList("صفر", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه");
    private static final List<String> between10And20 = Arrays.asList("ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده");
    private static final List<String> tens = Arrays.asList("", "ده", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود");
    private static final List<String> hundreds = Arrays.asList("", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد");

    @Override
    public String convert(String numberAsString) {
        if (StringUtility.isNullOrEmpty(numberAsString) || !numberAsString.matches("\\d*")) {
            return StringUtility.EMPTY;
        }

        numberAsString = NumberUtility.removeLeadingZerosFromNumber(numberAsString);
        if (numberAsString.equals(StringUtility.EMPTY)) {
            return ones.get(0);
        }
        var length = numberAsString.length();
        int placeIndex = 0;
        var builder = new StringBuilder();
        for (int i = length; i > 0; i -= 3) {
            var block = numberAsString.substring(Math.max(0, i - 3), i);
            var alphabetical = convertForHundreds(Integer.parseInt(block));
            var internalBuilder = new StringBuilder();
            if (!StringUtility.isNullOrEmpty(alphabetical)) {
                internalBuilder.append(alphabetical);
                if (placeIndex > 0) {
                    internalBuilder.append(StringUtility.SPACE).append(number_places.get(placeIndex));
                }
                if (!builder.isEmpty()) {
                    internalBuilder.append(AND);
                }
            }
            builder.insert(0,  internalBuilder);
            placeIndex++;
        }
        return builder.toString();
    }

    private static String convertForHundreds(int digits) {
        int number = Math.abs(digits) % 1000;
        int hundredsDigit = number / 100, remainder = number % 100;
        var builder = new StringBuilder();
        if (hundredsDigit > 0) {
            builder.append(hundreds.get(hundredsDigit));
            if (remainder > 0) {
                builder.append(AND);
            }
        }
        builder.append(convertForOnesAndTens(remainder));
        return builder.toString();
    }

    private static String convertForOnesAndTens(int digits) {
        int number = Math.abs(digits) % 100;
        if (number == 0) {
            return StringUtility.EMPTY;
        } else if (1 <= number && number < 10) {
            return ones.get(number);
        } else if (10 <= number && number < 20) {
            return between10And20.get(number - 10);
        } else {
            var builder = new StringBuilder(tens.get(number / 10));
            int remainder = number % 10;
            if (remainder > 0) {
                builder.append(AND).append(ones.get(remainder));
            }
            return builder.toString();
        }
    }
}
