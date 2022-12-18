package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ProductListMapper {

    private Map<String, List<Product>> productMap;

    public ProductListMapper mapList(List<Product> productList) {
        setProductMapByList(productList);
        mergeStoreProducts();
        return this;
    }

    private void mergeStoreProducts() {
        List<Product> newProductList = new ArrayList<>();
        for (Map.Entry<String, List<Product>> entry : productMap.entrySet()) {
            List<Product> productList = new ArrayList<>(entry.getValue());
            for (Product product : productList) {
                String productName = product.getProductName();
                long countProducts = productList.stream()
                        .filter(prodName -> prodName.getProductName().equals(productName)).count();

                if (newProductList.contains(product)) {
                    int index = newProductList.indexOf(product);
                    Product productNew = newProductList.get(index);
                    Double storePrice = product.getPrice();
                    Double storeNewPrice = productNew.getPrice();
                    Integer storeQuantity = product.getQuantity();
                    Integer storeNewQuantity = productNew.getQuantity();

                    Double newPrice = (storePrice + storeNewPrice) / countProducts;
                    Integer newQuantity = storeQuantity + storeNewQuantity;

                    productNew.setPrice(newPrice);
                    productNew.setQuantity(newQuantity);
                } else {
                    newProductList.add(product);
                }
            }
        }
        setProductMapByList(newProductList);
    }

    private void setProductMapByList(List<Product> newProductList) {
        productMap = newProductList.stream().collect(Collectors.groupingBy(Product::getNameStore));
    }
}
