package com.MAZYProduct.adapter.out.persistence.jpa;

import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

import java.util.List;

public class ProductMapper {
    private ProductMapper() {
    }

    static ProductJpaEntity toJpaEntity(Product product) {
        ProductJpaEntity jpaEntity = new ProductJpaEntity();
        if (product.getId() != null) {
            jpaEntity.setId(product.getId().value());
        }

        jpaEntity.setName(product.getName());
        jpaEntity.setDescription(product.getDescription());
        jpaEntity.setCategory(product.getCategory());
        jpaEntity.setPrice(product.getPrice().value());
        jpaEntity.setImage(product.getImage());

        return jpaEntity;
    }

    public static Product toModelEntity(ProductJpaEntity jpaEntity) {
        Product product = new Product();
        return updateModelEntity(product, jpaEntity);

    }

    public static Product updateModelEntity(Product product, ProductJpaEntity jpaEntity) {
        product.setId(new ProductId(jpaEntity.getId()));
        product.setName(jpaEntity.getName());
        product.setCategory(jpaEntity.getCategory());
        product.setDescription(jpaEntity.getDescription());
        product.setImage(jpaEntity.getImage());
        product.setPrice(new Price(jpaEntity.getPrice()));
        return product;
    }

    static List<Product> toModelEntities(List<ProductJpaEntity> jpaEntities) {
        return jpaEntities.stream().map(ProductMapper::toModelEntity).toList();
    }
}
