package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.example.Expectations.TEST_LIST;
import static org.example.Expectations.TEST_MAP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductListMapperTest {

    private final ProductListMapper productListMapper = Mockito.spy(ProductListMapper.class);

    @Test
    void testMappingList() {
        Map<String, List<Product>> productMap = productListMapper.mapList(TEST_LIST.getList());
        assertEquals(TEST_MAP.getMap(), productMap);
    }
}
