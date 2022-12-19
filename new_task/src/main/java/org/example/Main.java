package org.example;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String readPath = "new_task/src/main/resources/";
        String writePath = "new_task/";

        CsvParseService csvParseService = new CsvParseService();
        try {
            List<Product> objectList = csvParseService.parseCsvToList(readPath).getProductList();

            Map<String, List<Product>> productMap = new ProductListMapper().mapList(Objects.requireNonNull(objectList));

            csvParseService.writeMapToCsv(writePath, productMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
