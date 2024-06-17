package com.project.lovlind.conmon.utils.number;

import java.util.Random;

public abstract class NumberUtils {

  public static Random random = new Random();

  public static boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    return str.matches("\\d+");
  }

  public static String makeRandomNumberString(int length) {
    String randomValue = String.valueOf(random.nextInt(999999));
    return "0".repeat(length - randomValue.length()) + randomValue;
  }
}
