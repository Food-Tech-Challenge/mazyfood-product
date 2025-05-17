package com.MAZYProduct.application.service;

import com.MAZYProduct.application.port.in.CreateProductUseCase;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;

public class CreateProductService implements CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(String name, Category category, String description, Price price, String image) {
        Product product = new Product(name, category, description, price, image);
        productRepository.save(product);
        return product;
    }
}