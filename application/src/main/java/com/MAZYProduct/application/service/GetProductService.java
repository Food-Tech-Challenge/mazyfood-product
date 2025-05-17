package com.MAZYProduct.application.service;

import com.MAZYProduct.application.port.in.GetProductUseCase;
import com.MAZYProduct.application.port.in.ProductNotFoundException;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

public class GetProductService implements GetProductUseCase {
    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(ProductId productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("No product with ID " + productId.value()));
    }
}
