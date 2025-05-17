package com.MAZYProduct.adapter.in.rest;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;

import java.math.BigDecimal;

public record ProductInListWebModel(
        int id, String name, Category category, String description, BigDecimal price, String image) {

    public static ProductInListWebModel fromDomainModel(Product product) {
        return new ProductInListWebModel(
                product.getProductId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPriceDecimal(),
                product.getImage()
        );
    }
}