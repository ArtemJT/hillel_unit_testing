package org.example;

import org.example.math_library.test_result.TestResult;

public class Main {
    public static void main(String[] args) {
        TestRunner testRunner = new TestRunner();
        testRunner.testClassRun("SimpleMathLibrary");
        System.out.println(TestResult.parseFileToTestResult());
        System.out.println();
        testRunner.testClassRun("Fibonacci");
        System.out.println(TestResult.parseFileToTestResult());
        System.out.println();
        testRunner.testPackageRun("org/example/math_library/tests");
        System.out.println(TestResult.parseFileToTestResult());
    }
}