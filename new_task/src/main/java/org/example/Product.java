package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String nameStore;
    private String productName;
    private Double price;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!nameStore.equals(product.nameStore)) return false;
        return this.productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        int result = nameStore.hashCode();
        result = 31 * result + productName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s;%.2f;%d;", productName, price, quantity);
    }
}
