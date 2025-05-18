package com.MAZYProduct.adapter.out.persistence.jpa;

import com.MAZYProduct.adapter.out.persistence.jpa.JpaProductRepository;
import com.MAZYProduct.adapter.out.persistence.jpa.JpaProductSpringDataRepository;
import com.MAZYProduct.adapter.out.persistence.jpa.ProductMapper;
import com.MAZYProduct.adapter.out.persistence.jpa.ProductJpaEntity;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaProductRepositoryTest {

    private JpaProductSpringDataRepository springDataRepository;
    private JpaProductRepository jpaProductRepository;

    @BeforeEach
    void setup() {
        springDataRepository = mock(JpaProductSpringDataRepository.class);
        jpaProductRepository = new JpaProductRepository(springDataRepository);
    }

    @Test
    void save_shouldCallMapperAndSpringDataRepository() {
        ProductId id = new ProductId(1);
        Product product = new Product(id, "Coca Cola", Category.BEBIDA, "Refrigerante", null, "coca.png");
        ProductJpaEntity jpaEntity = mock(ProductJpaEntity.class);

        try (MockedStatic<ProductMapper> mapper = mockStatic(ProductMapper.class)) {
            mapper.when(() -> ProductMapper.toJpaEntity(product)).thenReturn(jpaEntity);

            jpaProductRepository.save(product);

            verify(springDataRepository).save(jpaEntity);
            mapper.verify(() -> ProductMapper.toJpaEntity(product));
            mapper.verify(() -> ProductMapper.updateModelEntity(product, jpaEntity));
        }
    }

    @Test
    void findById_shouldReturnProductWhenFound() {
        ProductJpaEntity jpaEntity = mock(ProductJpaEntity.class);
        Product expectedProduct = mock(Product.class);
        when(springDataRepository.findById(1)).thenReturn(Optional.of(jpaEntity));

        try (MockedStatic<ProductMapper> mapper = mockStatic(ProductMapper.class)) {
            mapper.when(() -> ProductMapper.toModelEntity(jpaEntity)).thenReturn(expectedProduct);

            Optional<Product> actual = jpaProductRepository.findById(new ProductId(1));

            assertTrue(actual.isPresent());
            assertEquals(expectedProduct, actual.get());
            mapper.verify(() -> ProductMapper.toModelEntity(jpaEntity));
        }
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        when(springDataRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Product> actual = jpaProductRepository.findById(new ProductId(1));

        assertFalse(actual.isPresent());
    }

    @Test
    void deleteById_shouldCallSpringDataRepository() {
        jpaProductRepository.deleteById(new ProductId(1));

        verify(springDataRepository).deleteById(1);
    }

    @Test
    void filterProductByCategory_shouldReturnMappedProducts() {
        List<ProductJpaEntity> jpaEntities = List.of(mock(ProductJpaEntity.class));
        List<Product> expectedProducts = List.of(mock(Product.class));
        Category category = Category.BEBIDA;

        when(springDataRepository.filterProductByCategory(category)).thenReturn(jpaEntities);

        try (MockedStatic<ProductMapper> mapper = mockStatic(ProductMapper.class)) {
            mapper.when(() -> ProductMapper.toModelEntities(jpaEntities)).thenReturn(expectedProducts);

            List<Product> actual = jpaProductRepository.filterProductByCategory(category);

            assertEquals(expectedProducts, actual);
            mapper.verify(() -> ProductMapper.toModelEntities(jpaEntities));
        }
    }
}
