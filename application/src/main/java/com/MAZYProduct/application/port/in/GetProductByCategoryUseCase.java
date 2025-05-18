package com.MAZYProduct.application.port.in;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;

import java.util.List;

public interface GetProductByCategoryUseCase {

    List<Product> filterProductByCategory(Category category);
}
