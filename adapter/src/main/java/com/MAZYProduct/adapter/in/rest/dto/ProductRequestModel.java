package com.MAZYProduct.adapter.in.rest.dto;

import java.math.BigDecimal;

public record ProductRequestModel(
        String name, String category, String description, BigDecimal price, String image) {
}