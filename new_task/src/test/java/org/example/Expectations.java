package org.example;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public enum Expectations {

    TEST_LIST(List.of(
            new Product("АТБ", "Гречка", 30.25, 120),
            new Product("АТБ", "Мука", 23.5, 80),
            new Product("АТБ", "Гречка", 31.25, 120),
            new Product("Сильпо", "Гречка", 31.25, 24),
            new Product("Сильпо", "Сахар", 22.20, 107))),

    TEST_MAP(Map.of(
            "АТБ", List.of(
                    new Product("АТБ", "Гречка", 30.75, 240),
                    new Product("АТБ", "Мука", 23.5, 80)),
            "Сильпо", List.of(
                    new Product("Сильпо", "Гречка", 31.25, 24),
                    new Product("Сильпо", "Сахар", 22.20, 107))));

    private List<Product> list;
    private Map<String, List<Product>> map;

    Expectations(List<Product> list) {
        this.list = list;
    }

    Expectations(Map<String, List<Product>> map) {
        this.map = map;
    }
}
