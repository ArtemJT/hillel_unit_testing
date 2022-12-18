package org.example.math_library.tests;

import org.example.math_library.SimpleMathLibrary;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleMathLibraryTest implements Printable {

    private final SimpleMathLibrary<Integer> simpleMathLibrary = new SimpleMathLibrary<>();

    @Test
    public boolean testNullInput() {
        try {
            simpleMathLibrary.add(0, null);
            return printResult(false);
        } catch (IllegalArgumentException e) {
            return printResult(true);
        }
    }

    @Test
    public boolean testAddTrue() {
        return printResult(simpleMathLibrary.add(-2, 3) == 1);
    }

    @Test
    public boolean testAddFailed() {
        return printResult(simpleMathLibrary.add(-2, 3) == 0);
    }

    @Test
    public boolean testMinusTrue() {
        return printResult(simpleMathLibrary.minus(-5, 3) == -8);
    }

    @Test
    public boolean testMinusFailed() {
        return printResult(simpleMathLibrary.minus(-5, 3) == 0);
    }

    @Test
    @Disabled
    public boolean testDisabled() {
        return false;
    }
}
