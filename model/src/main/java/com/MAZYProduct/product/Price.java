package com.MAZYProduct.product;

import java.math.BigDecimal;

public record Price(BigDecimal value) {

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    public Price multiply(int multiplicand) {
        return new Price(value.multiply(BigDecimal.valueOf(multiplicand)));
    }
}
