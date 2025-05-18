package com.MAZYProduct.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private ProductId id;
    private String name;
    private Category category;
    private String description;
    private Price price;
    private String image;

    public Product(String name, Category category, String description, Price price, String image) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getProductId() {
        return this.getId().value();
    }

    public String getCategoryName() {
        return this.getCategory().name();
    }

    public BigDecimal getPriceDecimal() {
        return this.getPrice().value();
    }
}
