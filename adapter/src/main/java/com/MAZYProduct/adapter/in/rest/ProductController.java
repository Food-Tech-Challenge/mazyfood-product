package com.MAZYProduct.adapter.in.rest;
import com.MAZYProduct.adapter.in.rest.dto.ProductRequestModel;
import com.MAZYProduct.adapter.in.rest.dto.ProductResponseModel;
import com.MAZYProduct.application.port.in.CreateProductUseCase;
import com.MAZYProduct.application.port.in.DeleteProductUseCase;
import com.MAZYProduct.application.port.in.EditProductUseCase;
import com.MAZYProduct.application.port.in.GetProductUseCase;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final EditProductUseCase editProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             GetProductUseCase getProductUseCase,
                             EditProductUseCase editProductUseCase,
                             DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.editProductUseCase = editProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponseModel> createProduct(@RequestBody ProductRequestModel productRequestModel) {
        Category category = Category.valueOf(productRequestModel.category());
        Price price = Price.of(productRequestModel.price());
        Product product = createProductUseCase.createProduct(productRequestModel.name(), category, productRequestModel.description(), price, productRequestModel.image());
        ProductResponseModel productResponseModel = ProductResponseModel.fromDomain(product);
        return ResponseEntity.ok(productResponseModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseModel> getProduct(@PathVariable int id) {
        ProductId productId = new ProductId(id);
        Product product = getProductUseCase.getProduct(productId);
        ProductResponseModel productResponseModel = ProductResponseModel.fromDomain(product);
        return ResponseEntity.ok(productResponseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseModel> editProduct(@PathVariable int id, @RequestBody ProductRequestModel productRequestModel) {
        ProductId productId = new ProductId(id);
        Category category = Category.valueOf(productRequestModel.category());
        Price price = Price.of(productRequestModel.price());
        Product product = editProductUseCase.editProduct(productId, productRequestModel.name(), category, productRequestModel.description(), price, productRequestModel.image());
        ProductResponseModel productResponseModel = ProductResponseModel.fromDomain(product);
        return ResponseEntity.ok(productResponseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        ProductId productId = new ProductId(id);
        deleteProductUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}