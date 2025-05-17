package com.MAZYProduct.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void testOfCreatesCorrectPrice() {
        BigDecimal value = new BigDecimal("10.50");
        Price price = Price.of(value);
        assertEquals(value, price.value());
    }

    @Test
    void testMultiplyReturnsCorrectPrice() {
        Price price = Price.of(new BigDecimal("5.00"));
        Price result = price.multiply(3);
        assertEquals(new BigDecimal("15.00"), result.value());
    }

    @Test
    void testMultiplyByZeroReturnsZeroPrice() {
        Price price = Price.of(new BigDecimal("8.25"));
        Price result = price.multiply(0);
        assertEquals(new BigDecimal("0.00"), result.value());
    }

}
