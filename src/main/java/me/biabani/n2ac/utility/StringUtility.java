package me.biabani.n2ac.utility;

public final class StringUtility {

    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
