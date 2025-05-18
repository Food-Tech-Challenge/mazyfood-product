package com.MAZYProduct.application.service;

import com.MAZYProduct.application.port.in.GetProductByCategoryUseCase;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;

import java.util.List;
import java.util.Objects;

public class GetProductByCategoryService implements GetProductByCategoryUseCase {

    private final ProductRepository productRepository;

    public GetProductByCategoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> filterProductByCategory(Category category) {
        Objects.requireNonNull(category, "'category' must not be null");
        return productRepository.filterProductByCategory(category);

    }
}
