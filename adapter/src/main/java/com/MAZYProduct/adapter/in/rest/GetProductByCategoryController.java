package com.MAZYProduct.adapter.in.rest;

import com.MAZYProduct.adapter.in.rest.ProductInListWebModel;
import com.MAZYProduct.application.port.in.CategoryNotFoundException;
import com.MAZYProduct.application.port.in.GetProductByCategoryUseCase;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")

public class GetProductByCategoryController {

    private final GetProductByCategoryUseCase getProductByCategoryUseCase;

    public GetProductByCategoryController(GetProductByCategoryUseCase getProductByCategoryUseCase) {
        this.getProductByCategoryUseCase = getProductByCategoryUseCase;
    }

    @GetMapping
    public List<ProductInListWebModel> getProductByCategory(@RequestParam(value = "category") String categoryString) {
        Category category;
        try {
            category = Category.valueOf(categoryString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CategoryNotFoundException("Category '" + categoryString + "' not found.");
        }

        List<Product> products = getProductByCategoryUseCase.filterProductByCategory(category);
        return products.stream().map(ProductInListWebModel::fromDomainModel).toList();
    }
}


