package com.MAZYProduct;

import com.MAZYProduct.application.port.in.*;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAppConfig {

    @Autowired
    ProductRepository productRepository;

    @Bean
    CreateProductUseCase getCreateProductUseCase() {
        return new CreateProductService(productRepository);
    }
    @Bean
    GetProductUseCase getProductUseCase() {
        return new GetProductService(productRepository);
    }

    @Bean
    EditProductUseCase getEditProductUseCase() {
        return new EditProductService(productRepository);
    }

    @Bean
    DeleteProductUseCase getDeleteProductUseCase() {
        return new DeleteProductService(productRepository);
    }

    @Bean
    GetProductByCategoryUseCase getProductByCategoryUseCase() {
        return new GetProductByCategoryService(productRepository);
    }
}
