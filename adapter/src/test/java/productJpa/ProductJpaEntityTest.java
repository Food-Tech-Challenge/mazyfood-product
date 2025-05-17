package com.MAZYProduct.adapter.out.persistence.jpa;

import com.MAZYProduct.product.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductJpaEntityTest {

    @Test
    void testSettersAndGetters() {
        ProductJpaEntity entity = new ProductJpaEntity();

        entity.setId(1);
        entity.setName("Coca Cola");
        entity.setCategory(Category.BEBIDA);
        entity.setDescription("Refrigerante");
        entity.setPrice(BigDecimal.valueOf(9.99));
        entity.setImage("coca.png");

        assertEquals(1, entity.getId());
        assertEquals("Coca Cola", entity.getName());
        assertEquals(Category.BEBIDA, entity.getCategory());
        assertEquals("Refrigerante", entity.getDescription());
        assertEquals(BigDecimal.valueOf(9.99), entity.getPrice());
        assertEquals("coca.png", entity.getImage());
    }
}