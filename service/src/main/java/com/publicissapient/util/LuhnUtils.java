package com.publicissapient.util;

import java.util.Arrays;

public class LuhnUtils {

  public static boolean luhnCheck(String cNumber) {
    int[] cardIntArray = new int[cNumber.length()];
    int sum;

    for (int i = 0; i < cNumber.length(); i++) {
      char c = cNumber.charAt(i);
      cardIntArray[i] = Integer.parseInt("" + c);
    }

    for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
      int num = cardIntArray[i];
      num = num * 2;
      if (num > 9) {
        num = num % 10 + num / 10;
      }
      cardIntArray[i] = num;
    }

    sum = Arrays.stream(cardIntArray).sum();
    return sum % 10 == 0;
  }
}
