package com.MAZYProduct.adapter.out.persistence.jpa;

import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(name = "persistence", havingValue = "postgresql", matchIfMissing = true)
@Repository
public class JpaProductRepository implements ProductRepository {

    private final JpaProductSpringDataRepository jpaProductSpringDataRepository;

    public JpaProductRepository(JpaProductSpringDataRepository jpaProductSpringDataRepository) {
        this.jpaProductSpringDataRepository = jpaProductSpringDataRepository;
    }

    @Override
    @Transactional
    public void save(Product product) {
        ProductJpaEntity productJpaEntity = ProductMapper.toJpaEntity(product);
        jpaProductSpringDataRepository.save(productJpaEntity);
        ProductMapper.updateModelEntity(product, productJpaEntity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        Optional<ProductJpaEntity> productJpaEntity = jpaProductSpringDataRepository.findById(id.value());
        return productJpaEntity.map(ProductMapper::toModelEntity);
    }

    @Override
    public void deleteById(ProductId id) {
        jpaProductSpringDataRepository.deleteById(id.value());
    }

    @Transactional
    @Override
    public List<Product> filterProductByCategory(Category category) {
        List<ProductJpaEntity> entities =
                jpaProductSpringDataRepository.filterProductByCategory(category);

        return ProductMapper.toModelEntities(entities);
    }
}

