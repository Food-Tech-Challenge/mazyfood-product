package com.MAZYProduct.adapter.in.rest.dto;

import com.MAZYProduct.product.Product;

import java.math.BigDecimal;

public record ProductResponseModel(int id, String name, String category, String description, BigDecimal price,
                                   String image) {
    public static ProductResponseModel fromDomain(Product product) {
        return new ProductResponseModel(
                product.getProductId(),
                product.getName(),
                product.getCategoryName(),
                product.getDescription(),
                product.getPriceDecimal(),
                product.getImage()
        );
    }
}
