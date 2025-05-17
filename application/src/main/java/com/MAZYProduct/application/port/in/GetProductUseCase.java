package com.MAZYProduct.application.port.in;

import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

public interface GetProductUseCase {
    Product getProduct(ProductId productId);
}
