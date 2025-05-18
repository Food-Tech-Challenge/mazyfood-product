package com.MAZYProduct.adapter.out.persistence.jpa;

import com.MAZYProduct.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductSpringDataRepository extends JpaRepository<ProductJpaEntity, Integer> {

    @Query("SELECT p FROM ProductJpaEntity p WHERE p.category = :category")
    List<ProductJpaEntity> filterProductByCategory(@Param("category") Category category);
}
