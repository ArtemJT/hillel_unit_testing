package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassFinder {
    private static final char PKG_SEPARATOR = '.';
    private static final char DIR_SEPARATOR = '/';
    private static final String CLASS_FILE_SUFFIX = ".java";
    private static final String PATH_FILE_PREFIX = "src/main/java/";
    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

    private ClassFinder() {
    }

    /**
     * Возвращает список классов в проекте
     */
    public static List<Class<?>> findClassesInProject(String scannedPackage) {
        String scannedPath = PATH_FILE_PREFIX + scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        File scannedDir = new File(scannedPath);
        if (!scannedDir.exists()) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File file : Objects.requireNonNull(scannedDir.listFiles())) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }

    /**
     * Возвращает список классов в пакете
     */
    public static List<Class<?>> findClassesInDirectory(String scannedPackage) {
        String scannedPath = PATH_FILE_PREFIX + scannedPackage;
        File scannedDir = new File(scannedPath);
        if (!scannedDir.exists()) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File file : Objects.requireNonNull(scannedDir.listFiles())) {
            String resource = scannedPackage.replace(DIR_SEPARATOR, PKG_SEPARATOR) + PKG_SEPARATOR + file.getName();
            if (resource.endsWith(CLASS_FILE_SUFFIX)) {
                int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
                String className = resource.substring(0, endIndex);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }


    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }
}
