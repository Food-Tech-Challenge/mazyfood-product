package com.MAZYProduct.adapter.out.persistence.jpa;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void shouldMapProductToJpaEntity() {
        Product product = new Product(
                new ProductId(1),
                "Coca Cola",
                Category.BEBIDA,
                "Refrigerante",
                Price.of(BigDecimal.valueOf(9.99)),
                "coca.png"
        );

        ProductJpaEntity jpaEntity = ProductMapper.toJpaEntity(product);

        assertEquals(1, jpaEntity.getId());
        assertEquals("Coca Cola", jpaEntity.getName());
        assertEquals(Category.BEBIDA, jpaEntity.getCategory());
        assertEquals("Refrigerante", jpaEntity.getDescription());
        assertEquals(BigDecimal.valueOf(9.99), jpaEntity.getPrice());
        assertEquals("coca.png", jpaEntity.getImage());
    }

    @Test
    void shouldMapJpaEntityToProduct() {
        ProductJpaEntity jpaEntity = new ProductJpaEntity();
        jpaEntity.setId(1);
        jpaEntity.setName("Coca Cola");
        jpaEntity.setCategory(Category.BEBIDA);
        jpaEntity.setDescription("Refrigerante");
        jpaEntity.setPrice(BigDecimal.valueOf(9.99));
        jpaEntity.setImage("coca.png");

        Product product = ProductMapper.toModelEntity(jpaEntity);

        assertEquals(1, product.getId().value());
        assertEquals("Coca Cola", product.getName());
        assertEquals(Category.BEBIDA, product.getCategory());
        assertEquals("Refrigerante", product.getDescription());
        assertEquals(BigDecimal.valueOf(9.99), product.getPrice().value());
        assertEquals("coca.png", product.getImage());
    }

    @Test
    void shouldMapListOfJpaEntitiesToProducts() {
        ProductJpaEntity jpaEntity = new ProductJpaEntity();
        jpaEntity.setId(2);
        jpaEntity.setName("Coca Cola");
        jpaEntity.setCategory(Category.BEBIDA);
        jpaEntity.setDescription("Refrigerante");
        jpaEntity.setPrice(BigDecimal.valueOf(9.99));
        jpaEntity.setImage("coca.png");

        List<Product> products = ProductMapper.toModelEntities(List.of(jpaEntity));

        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals(2, product.getId().value());
        assertEquals("Coca Cola", product.getName());
        assertEquals(Category.BEBIDA, product.getCategory());
    }
}
