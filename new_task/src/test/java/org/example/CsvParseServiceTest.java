package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.example.Expectations.TEST_LIST;
import static org.example.Expectations.TEST_MAP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvParseServiceTest {

    private final CsvParseService csvParseService = new CsvParseService();

    @Test
    void testWrongDirectoryPath() {
        assertThrows(FileNotFoundException.class, () -> csvParseService.parseCsvToList("-"));
        assertThrows(FileNotFoundException.class, () -> csvParseService.writeMapToCsv("-", TEST_MAP.getMap()));
    }

    @Test
    void testGettingListFromCsvFile() throws FileNotFoundException {
        assertEquals(TEST_LIST.getList(), csvParseService.parseCsvToList("src/test/java/test_resources").getProductList());
    }
}
