package com.project.lovlind.conmon.utils.number;

public abstract class NumberUtils {
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }
}
