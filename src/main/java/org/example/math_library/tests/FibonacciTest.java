package org.example.math_library.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.example.math_library.Fibonacci.getFibonacci;

public class FibonacciTest implements Printable {

    @Test
    public boolean testNullInput() {
        try {
            getFibonacci(null);
            return printResult(false);
        } catch (IllegalArgumentException e) {
            return printResult(true);
        }
    }

    @Test
    public boolean testNegativeInput() {
        try {
            getFibonacci(-1);
            return printResult(false);
        } catch (IllegalArgumentException e) {
            return printResult(true);
        }
    }

    @Test
    @Disabled
    public boolean testDisabled() {
        return false;
    }


    @Test
    public boolean testFailed() {
        return printResult(getFibonacci(0) == 1);
    }

    @Test
    public boolean testTrue() {
        return printResult(getFibonacci(7) == 13);
    }
}
