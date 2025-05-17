package product;

import com.MAZYProduct.product.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testEnumValuesExist() {
        assertNotNull(Category.valueOf("LANCHE"));
        assertNotNull(Category.valueOf("ACOMPANHAMENTO"));
        assertNotNull(Category.valueOf("BEBIDA"));
        assertNotNull(Category.valueOf("SOBREMESA"));
    }

    @Test
    void testEnumValueOfThrowsExceptionForInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Category.valueOf("lanche");
        });
    }

}
