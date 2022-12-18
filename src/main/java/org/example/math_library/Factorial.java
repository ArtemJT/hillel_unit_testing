package org.example.math_library;

import java.math.BigInteger;

public class Factorial {

    private Factorial() {}

    public static String getFactorial(Integer n) {
        if (n == null || n < 0) {
            throw new IllegalArgumentException();
        }

        BigInteger binInt = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            binInt = binInt.multiply(BigInteger.valueOf(i));
        }

        return binInt.toString();
    }
}
