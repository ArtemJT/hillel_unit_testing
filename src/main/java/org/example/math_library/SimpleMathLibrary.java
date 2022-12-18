package org.example.math_library;

public class SimpleMathLibrary<T extends Number> {

    public double add(T firstNumber, T secondNumber) {
        nullCheckArgs(firstNumber, secondNumber);
        return firstNumber.doubleValue() + secondNumber.doubleValue();
    }

    public double minus(T firstNumber, T secondNumber) {
        nullCheckArgs(firstNumber, secondNumber);
        return firstNumber.doubleValue() - secondNumber.doubleValue();
    }

    private void nullCheckArgs(T firstNumber, T secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException("ARGUMENT IS NULL");
        }
    }
}
