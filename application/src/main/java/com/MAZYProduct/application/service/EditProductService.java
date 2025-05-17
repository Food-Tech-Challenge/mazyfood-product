package com.MAZYProduct.application.service;

import com.MAZYProduct.application.port.in.EditProductUseCase;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

public class EditProductService implements EditProductUseCase {
    private final ProductRepository productRepository;

    public EditProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product editProduct(ProductId productId, String name, Category category, String description, Price price, String image) {
        Product product = new Product(productId, name, category, description, price, image);
        productRepository.save(product);
        return product;
    }
}
