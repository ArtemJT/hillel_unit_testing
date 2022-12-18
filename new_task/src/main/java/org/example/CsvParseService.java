package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@NoArgsConstructor
public class CsvParseService {

    @Getter
    private final List<Product> productList = new ArrayList<>();
    private static final String FILE_SUFFIX = ".csv";

    private void checkIsDirectoryExist(String path) throws FileNotFoundException {
        if (!Files.exists(Path.of(path))) {
            throw new FileNotFoundException(String.format("DIRECTORY NOT FOUND ON PATH: {%s}%n", path));
        }
    }

    public CsvParseService parseCsvToList(@NonNull String path) throws FileNotFoundException {
        checkIsDirectoryExist(path);

        File dir = new File(path);
        File[] listFiles = dir.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            throw new FileNotFoundException(String.format("DIRECTORY {%s} IS EMPTY", path));
        } else {
            for (File file : listFiles) {
                String fileName = file.getName();
                if (!fileName.endsWith(FILE_SUFFIX)) {
                    throw new FileNotFoundException(String.format("NO CSV FILES IN DIRECTORY: %s", path));
                } else {
                    parseFromFile(file);
                }
            }
        }
        return this;
    }

    public void writeMapToCsv(@NonNull String path, Map<String, List<Product>> productMap) throws FileNotFoundException {
        checkIsDirectoryExist(path);

        for (Map.Entry<String, List<Product>> entry : productMap.entrySet()) {
            String fileName = entry.getKey().toLowerCase(Locale.ROOT) + "_res" + FILE_SUFFIX;
            StringJoiner sj = new StringJoiner("\n");
            sj.add("НАИМЕНОВАНИЕ;ЦЕНА;ШТ;");
            entry.getValue().forEach(product -> sj.add(product.toString()));


            File file = new File(path + fileName);
            try {
                if (file.createNewFile()) {
                    System.out.println("FILE " + fileName + " WAS CREATED IN DIRECTORY: " + path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(sj));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] splitStrings = line.split(";");
                Product product = new Product();

                product.setNameStore(splitStrings[0]);
                product.setProductName(splitStrings[1]);
                product.setPrice(Double.parseDouble(splitStrings[2]));
                product.setQuantity(Integer.parseInt(splitStrings[3]));
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
