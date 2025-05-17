package product;

import com.MAZYProduct.adapter.in.rest.GetProductByCategoryController;
import com.MAZYProduct.adapter.in.rest.GlobalExceptionHandler;
import com.MAZYProduct.application.port.in.GetProductByCategoryUseCase;
import com.MAZYProduct.product.*;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;

class GetProductByCategoryControllerTest {

    private final GetProductByCategoryUseCase getProductByCategoryUseCase = Mockito.mock(GetProductByCategoryUseCase.class);

    @BeforeEach
    void setup() {
        GetProductByCategoryController controller = new GetProductByCategoryController(getProductByCategoryUseCase);
        RestAssuredMockMvc.standaloneSetup(controller, new GlobalExceptionHandler());
    }

    @Test
    void shouldReturnProductsByCategorySuccessfully() {
        Product product1 = new Product(new ProductId(1), "Coca Cola", Category.BEBIDA, "Refrigerante", Price.of(BigDecimal.valueOf(9.99)), "coca.png");
        Product product2 = new Product(new ProductId(2), "Guaraná", Category.BEBIDA, "Refrigerante", Price.of(BigDecimal.valueOf(7.99)), "guarana.png");

        when(getProductByCategoryUseCase.filterProductByCategory(Category.BEBIDA))
                .thenReturn(List.of(product1, product2));

        given()
                .queryParam("category", "BEBIDA")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("[0].name", org.hamcrest.Matchers.equalTo("Coca Cola"))
                .body("[1].name", org.hamcrest.Matchers.equalTo("Guaraná"))
                .body("[0].id", org.hamcrest.Matchers.equalTo(1))
                .body("[1].id", org.hamcrest.Matchers.equalTo(2));
    }

    @Test
    void shouldReturn400WhenCategoryIsInvalid() {
        given()
                .when()
                .get("/products?category=invalida")
                .then()
                .statusCode(400)
                .body("error", Matchers.equalTo("Category 'invalida' not found."));
    }

    @Test
    void shouldReturn400WhenCategoryIsMissing() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(400);
    }
}
