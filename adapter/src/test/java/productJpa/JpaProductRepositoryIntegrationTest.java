package com.MAZYProduct.adapter.out.persistence.jpa;


import com.MAZYProduct.product.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class JpaProductRepositoryIntegrationTest {

    @Autowired
    private JpaProductSpringDataRepository springDataRepository;

    @Test
    void testFilterProductByCategoryReturnsCorrectResults() {
        ProductJpaEntity product1 = new ProductJpaEntity();
        product1.setName("Coca Cola");
        product1.setCategory(Category.BEBIDA);
        product1.setDescription("Refrigerante");
        product1.setPrice(new BigDecimal("9.99"));
        product1.setImage("coca.png");

        ProductJpaEntity product2 = new ProductJpaEntity();
        product2.setName("Guaraná");
        product2.setCategory(Category.BEBIDA);
        product2.setDescription("Refrigerante");
        product2.setPrice(new BigDecimal("9.00"));
        product2.setImage("guaranazin.png");

        ProductJpaEntity product3 = new ProductJpaEntity();
        product3.setName("X-Bacon");
        product3.setCategory(Category.LANCHE);
        product3.setDescription("Hamburguer");
        product3.setPrice(new BigDecimal("19.00"));
        product3.setImage("burguer.png");

        springDataRepository.saveAll(List.of(product1, product2, product3));

        List<ProductJpaEntity> bebidas = springDataRepository.filterProductByCategory(Category.BEBIDA);

        assertEquals(2, bebidas.size());
        assertEquals("Coca Cola", bebidas.get(0).getName());
    }

    @Test
    void testFilterProductByCategoryReturnsEmptyListWhenNoProductsExist() {
        List<ProductJpaEntity> result = springDataRepository.filterProductByCategory(Category.SOBREMESA);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
