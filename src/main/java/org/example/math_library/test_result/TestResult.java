package org.example.math_library.test_result;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Getter
public class TestResult {

    private static long executionTime;
    private static long countTests;
    private static long countSuccessTests;
    private static long countFailedTests;

    private TestResult() {
    }

    public static TestResult parseFileToTestResult() {
        String pathname = "src/main/resources/ResultTable.txt";
        File file = new File(pathname);
        if (!file.exists()) System.out.println("file not found at given path: ".toUpperCase() + '{' + pathname + '}');

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String testsFound = " tests found";
                String testsSuccessful = " tests successful";
                String testsFailed = " tests failed";
                String executTime = " ms";
                int indexStart = 2;
                int indexEnd;
                if (line.contains(testsFound)) {
                    indexEnd = line.indexOf(testsFound);
                    countTests = Long.parseLong(line.substring(indexStart, indexEnd));
                } else if (line.contains(testsSuccessful)) {
                    indexEnd = line.indexOf(testsSuccessful);
                    countSuccessTests = Long.parseLong(line.substring(indexStart, indexEnd));
                } else if (line.contains(testsFailed)) {
                    indexEnd = line.indexOf(testsFailed);
                    countFailedTests = Long.parseLong(line.substring(indexStart, indexEnd));
                } else if (line.contains(executTime)) {
                    indexStart = line.indexOf("after ") + 6;
                    indexEnd = line.indexOf(executTime);
                    executionTime = Long.parseLong(line.substring(indexStart, indexEnd));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TestResult();
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "executionTime=" + executionTime +
                ", countTests=" + countTests +
                ", countSuccessTests=" + countSuccessTests +
                ", countFailedTests=" + countFailedTests +
                '}';
    }
}
