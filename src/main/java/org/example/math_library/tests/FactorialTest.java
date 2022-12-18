package org.example.math_library.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.example.math_library.Factorial.getFactorial;

public class FactorialTest implements Printable {

    @Test
    public boolean testNullInput() {
        try {
            getFactorial(null);
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
    public boolean testNegativeInput() {
        try {
            getFactorial(-1);
            return printResult(false);
        } catch (IllegalArgumentException e) {
            return printResult(true);
        }

    }

    @Test
    public boolean testFailed() {
        return printResult(getFactorial(0).equals("0"));
    }

    @Test
    public boolean testTrue() {
        return printResult(getFactorial(13).equals("6227020800"));
    }
}
