package product;

import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductWithAllFields() {
        Category category = Category.BEBIDA;
        Price price = Price.of(BigDecimal.valueOf(9.99));
        Product product = new Product("Coca Cola", category, "Refrigerante", price, "coca.png");
        assertNull(product.getId());
        assertEquals("Coca Cola", product.getName());
        assertEquals("Refrigerante", product.getDescription());
        assertEquals("coca.png", product.getImage());
        assertEquals("BEBIDA", product.getCategoryName());
        assertEquals(BigDecimal.valueOf(9.99), product.getPriceDecimal());
    }

    @Test
    void shouldReturnCorrectCategoryName() {
        Product product = new Product();
        product.setCategory(Category.SOBREMESA);
        assertEquals("SOBREMESA", product.getCategoryName());
    }

    @Test
    void shouldReturnCorrectPriceDecimal() {
        Price price = Price.of(BigDecimal.valueOf(7.50));
        Product product = new Product();
        product.setPrice(price);
        assertEquals(BigDecimal.valueOf(7.50), product.getPriceDecimal());
    }

    @Test
    void shouldReturnCorrectProductId() {
        ProductId id = new ProductId(42);
        Product product = new Product();
        product.setId(id);
        assertEquals(42, product.getProductId());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenGettingPriceDecimalIfPriceIsNull() {
        Product product = new Product();
        product.setPrice(null);
        assertThrows(NullPointerException.class, product::getPriceDecimal);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenGettingCategoryNameIfCategoryIsNull() {
        Product product = new Product();
        product.setCategory(null);
        assertThrows(NullPointerException.class, product::getCategoryName);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenGettingProductIdIfIdIsNull() {
        Product product = new Product();
        product.setId(null);
        assertThrows(NullPointerException.class, product::getProductId);
    }
}
