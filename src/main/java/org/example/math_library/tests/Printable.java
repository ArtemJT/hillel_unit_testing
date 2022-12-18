package org.example.math_library.tests;

public interface Printable {

    default boolean printResult(boolean result) {
        System.out.println(result ? "OK" : "NOK");
        return result;
    }
}
