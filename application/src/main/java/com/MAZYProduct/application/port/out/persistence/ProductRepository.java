package com.MAZYProduct.application.port.out.persistence;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(ProductId id);

    void deleteById(ProductId id);

    List<Product> filterProductByCategory(Category category);
}