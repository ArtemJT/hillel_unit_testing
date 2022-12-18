package org.example.math_library;

public class Fibonacci {

    private Fibonacci() {}

    public static Integer getFibonacci(Integer n) {
        if (n == null || n < 0) throw new IllegalArgumentException();

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
    }
}
