package com.MAZYProduct.application.service;

import com.MAZYProduct.application.port.in.DeleteProductUseCase;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.ProductId;

public class DeleteProductService implements DeleteProductUseCase {
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProduct(ProductId productId) {
        productRepository.deleteById(productId);
    }
}
