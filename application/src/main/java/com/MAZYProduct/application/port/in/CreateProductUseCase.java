package com.MAZYProduct.application.port.in;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;

public interface CreateProductUseCase {
    Product createProduct(String name, Category category, String description, Price price, String image);
}
