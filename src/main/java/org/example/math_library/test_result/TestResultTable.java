package org.example.math_library.test_result;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Getter
@Setter
public class TestResultTable {

    private long executionTime;
    private long startTime;
    private long countTestClasses;
    private long countTests;
    private long countSkippedTests;
    private long countStartedTests;
    private long countSuccessTests;
    private long countFailedTests;
    private String testResultTableMessage;

    public TestResultTable() {
        this.startTime = System.currentTimeMillis();
    }

    public void increaseCountTestClasses() {
        countTestClasses++;
    }

    public void increaseCountTests(long count) {
        countTests += count;
    }

    public void increaseCountSkippedTests(long count) {
        countSkippedTests += count;
    }

    public void increaseCountStartedTests() {
        countStartedTests++;
    }

    public void increaseCountSuccessTests() {
        countSuccessTests++;
    }

    public void increaseCountFailedTests() {
        countFailedTests++;
    }

    public void setExecutionTime() {
        long endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
    }

    public void makeResultTable() throws ClassNotFoundException {
        if (countTestClasses == 0) {
            throw new ClassNotFoundException("NO TEST CLASSES!");
        }
        String pattern = "[ %s ]%n";
        LocalDateTime date = LocalDateTime.now();
        testResultTableMessage = String.format("%s%nTest run finished after %d ms%n", date, executionTime) +
                String.format(pattern, countTestClasses + " containers found") +
                String.format(pattern, countTests + " tests found") +
                String.format(pattern, countSkippedTests + " tests skipped") +
                String.format(pattern, countStartedTests + " tests started") +
                String.format(pattern, countSuccessTests + " tests successful") +
                String.format(pattern, countFailedTests + " tests failed").trim();
    }

    public void writeTestTableIntoFile() throws IOException {
        String path = "src/main/resources/";
        String fileName = "ResultTable";
        String ext = ".txt";
        File file = new File(path + fileName + ext);
        if (file.createNewFile()) {
            System.out.printf("%nFILE {%s%s} HAS BEEN CREATED IN {%s}%n", fileName, ext, path);
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(String.valueOf(testResultTableMessage));
        }
    }
}
