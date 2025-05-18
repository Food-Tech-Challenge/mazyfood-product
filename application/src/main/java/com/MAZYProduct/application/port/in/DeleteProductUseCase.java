package com.MAZYProduct.application.port.in;

import com.MAZYProduct.product.ProductId;

public interface DeleteProductUseCase {
    void deleteProduct(ProductId productId);
}
