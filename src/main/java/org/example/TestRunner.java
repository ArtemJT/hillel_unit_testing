package org.example;

import lombok.NonNull;
import org.example.math_library.test_result.TestResultTable;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    private List<Class<?>> classList;
    private TestResultTable testResultTable;

    public void testClassRun(@NonNull String className) {
        classList = ClassFinder.findClassesInProject(TestRunner.class.getPackageName());

        getTestResultInstance();
        for (Class<?> aClass : classList) {
            String testClassName = className + "Test";
            if (testClassName.equals(aClass.getSimpleName())) {
                testRun(aClass);
            }
        }
        testResultTable.setExecutionTime();
        makeResultTable();
        writeTestTableIntoFile();
        System.out.println(testResultTable.getTestResultTableMessage());
    }

    public void testPackageRun(@NonNull String testsDirectory) {
        classList = ClassFinder.findClassesInDirectory(testsDirectory);

        getTestResultInstance();
        for (Class<?> aClass : classList) {
            testRun(aClass);
        }
        testResultTable.setExecutionTime();
        makeResultTable();
        writeTestTableIntoFile();
        System.out.println(testResultTable.getTestResultTableMessage());
    }

    private void writeTestTableIntoFile() {
        try {
            testResultTable.writeTestTableIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getTestResultInstance() {
        testResultTable = new TestResultTable();
    }

    private void makeResultTable() {
        try {
            testResultTable.makeResultTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testRun(Class<?> aClass) {
        Method[] declaredMethods = aClass.getDeclaredMethods();
        if (isTestClass(declaredMethods)) {
            System.out.println(aClass.getSimpleName() + " STARTED");
            countTestClasses(declaredMethods);
            countDisabledMethods(declaredMethods);
            try {
                startTests(aClass);
            } catch (InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isTestClass(Method[] declaredMethods) {
        boolean isTest = Arrays.stream(declaredMethods)
                .anyMatch(method -> method.isAnnotationPresent(Test.class));
        if (isTest) {
            testResultTable.increaseCountTestClasses();
            return true;
        } else {
            return false;
        }
    }

    private void countTestClasses(Method[] declaredMethods) {
        long count = Arrays.stream(declaredMethods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .count();
        if (count > 0) {
            testResultTable.increaseCountTests(count);
        }
    }

    private void countDisabledMethods(Method[] declaredMethods) {
        long count = Arrays.stream(declaredMethods)
                .filter(method -> method.isAnnotationPresent(Disabled.class))
                .count();
        if (count > 0) {
            testResultTable.increaseCountSkippedTests(count);
        }
    }

    private void startTests(Class<?> clazz)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class) && !method.isAnnotationPresent(Disabled.class)) {
                System.out.println(method.getName() + " started...");
                boolean invoke = (boolean) method.invoke(clazz.getDeclaredConstructor().newInstance());
                testResultTable.increaseCountStartedTests();
                if (invoke) {
                    testResultTable.increaseCountSuccessTests();
                } else {
                    testResultTable.increaseCountFailedTests();
                }
            }
        }
    }
}

