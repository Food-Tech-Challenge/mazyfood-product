package com.MAZYProduct.application.port.in;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;

public interface EditProductUseCase {
    Product editProduct(ProductId productId, String name, Category category, String description, Price price, String image);
}
