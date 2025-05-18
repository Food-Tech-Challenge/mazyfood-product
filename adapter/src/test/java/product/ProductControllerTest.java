package product;

import com.MAZYProduct.product.*;
import com.MAZYProduct.adapter.in.rest.ProductController;
import com.MAZYProduct.application.port.in.*;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    private final CreateProductUseCase createProductUseCase = Mockito.mock(CreateProductUseCase.class);
    private final GetProductUseCase getProductUseCase = Mockito.mock(GetProductUseCase.class);
    private final EditProductUseCase editProductUseCase = Mockito.mock(EditProductUseCase.class);
    private final DeleteProductUseCase deleteProductUseCase = Mockito.mock(DeleteProductUseCase.class);

    @BeforeEach
    void setup() {
        ProductController controller = new ProductController(
                createProductUseCase, getProductUseCase, editProductUseCase, deleteProductUseCase
        );
        RestAssuredMockMvc.standaloneSetup(controller);
    }

    @Test
    void shouldCreateProductSuccessfully() {
        ProductId productId = new ProductId(1);
        Product mockProduct = new Product(productId, "Coca Cola", Category.BEBIDA, "Refrigerante", Price.of(BigDecimal.valueOf(9.99)), "coca.png");
        when(createProductUseCase.createProduct(any(), any(), any(), any(), any()))
                .thenReturn(mockProduct);

        String json = """
        {
          "name": "Coca Cola",
          "category": "BEBIDA",
          "description": "Refrigerante",
          "price": 9.99,
          "image": "coca.png"
        }
        """;

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(json)
                .when()
                .post("/products")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("name", org.hamcrest.Matchers.equalTo("Coca Cola"))
                .body("category", org.hamcrest.Matchers.equalTo("BEBIDA"))
                .body("price", org.hamcrest.Matchers.equalTo(9.99f));
    }

    @Test
    void shouldGetProductByIdSuccessfully() {
        ProductId productId = new ProductId(1);
        Product mockProduct = new Product(productId, "Coca Cola", Category.BEBIDA, "Refrigerante", Price.of(BigDecimal.valueOf(9.99)), "coca.png");
        when(getProductUseCase.getProduct(any())).thenReturn(mockProduct);

        given()
                .when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("name", org.hamcrest.Matchers.equalTo("Coca Cola"))
                .body("category", org.hamcrest.Matchers.equalTo("BEBIDA"));
    }

    @Test
    void shouldEditProductSuccessfully() {
        ProductId productId = new ProductId(1);
        Product mockProduct = new Product(productId, "Coca Cola Zero", Category.BEBIDA, "Sem açúcar", Price.of(BigDecimal.valueOf(10.99)), "coca_zero.png");
        when(editProductUseCase.editProduct(any(), any(), any(), any(), any(), any())).thenReturn(mockProduct);

        String json = """
        {
          "name": "Coca Cola Zero",
          "category": "BEBIDA",
          "description": "Sem açúcar",
          "price": 10.99,
          "image": "coca_zero.png"
        }
        """;

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(json)
                .when()
                .put("/products/1")
                .then()
                .statusCode(200)
                .body("name", org.hamcrest.Matchers.equalTo("Coca Cola Zero"))
                .body("price", org.hamcrest.Matchers.equalTo(10.99f));
    }

    @Test
    void shouldDeleteProductSuccessfully() {
        Mockito.doNothing().when(deleteProductUseCase).deleteProduct(any());

        given()
                .when()
                .delete("/products/1")
                .then()
                .statusCode(204);
    }
}
