package Product.service;

import com.MAZYProduct.application.port.in.ProductNotFoundException;
import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.application.service.*;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Price;
import com.MAZYProduct.product.ProductId;
import com.MAZYProduct.product.Product;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    ProductRepository productRepository = mock(ProductRepository.class);

    @Nested
    class CreateProductServiceTest {

        @Test
        void testCreateProduct() {
            CreateProductService service = new CreateProductService(productRepository);

            String name = "Fanta";
            Category category = Category.BEBIDA;
            String description = "Refrigerante de laranja";
            Price price = new Price(BigDecimal.valueOf(4.99));
            String image = "fanta.png";

            Product result = service.createProduct(name, category, description, price, image);

            verify(productRepository).save(result);
            assertEquals(name, result.getName());
            assertEquals(category, result.getCategory());
            assertEquals(description, result.getDescription());
            assertEquals(price, result.getPrice());
            assertEquals(image, result.getImage());
        }
    }

    @Nested
    class EditProductServiceTest {

        @Test
        void testEditProduct() {
            EditProductService service = new EditProductService(productRepository);

            ProductId productId = new ProductId(1);
            String name = "Produto Editado";
            Category category = Category.BEBIDA;
            String description = "Nova descrição";
            Price price = new Price(BigDecimal.valueOf(5.99));
            String image = "imagem.png";

            Product result = service.editProduct(productId, name, category, description, price, image);

            verify(productRepository).save(result);
            assertEquals(productId, result.getId());
            assertEquals(name, result.getName());
            assertEquals(category, result.getCategory());
            assertEquals(description, result.getDescription());
            assertEquals(price, result.getPrice());
            assertEquals(image, result.getImage());
        }
    }

    @Nested
    class DeleteProductServiceTest {

        @Test
        void testDeleteProduct() {
            DeleteProductService service = new DeleteProductService(productRepository);

            ProductId productId = new ProductId(1);

            service.deleteProduct(productId);

            verify(productRepository).deleteById(productId);
        }
    }

    @Nested
    class GetProductServiceTest {

        @Test
        void testGetProduct_Success() {
            GetProductService service = new GetProductService(productRepository);

            ProductId productId = new ProductId(1);
            Product product = new Product("Coca Cola", Category.BEBIDA, "Refrigerante",
                    new Price(BigDecimal.valueOf(9.99)), "coca.png");
            product.setId(productId);

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));

            Product result = service.getProduct(productId);

            assertEquals(product, result);
        }

        @Test
        void testGetProduct_NotFound() {
            GetProductService service = new GetProductService(productRepository);
            ProductId productId = new ProductId(999);

            when(productRepository.findById(productId)).thenReturn(Optional.empty());

            ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> {
                service.getProduct(productId);
            });

            assertEquals("No product with ID 999", ex.getMessage());
        }
    }

    @Nested
    class GetProductByCategoryServiceTest {

        @Test
        void testFilterProductByCategory() {
            GetProductByCategoryService service = new GetProductByCategoryService(productRepository);

            Category category = Category.BEBIDA;
            Product product = new Product("Pepsi", category, "Refrigerante", new Price(BigDecimal.valueOf(6.99)), "pepsi.png");

            when(productRepository.filterProductByCategory(category)).thenReturn(List.of(product));

            List<Product> result = service.filterProductByCategory(category);

            assertEquals(1, result.size());
            assertEquals("Pepsi", result.get(0).getName());
        }

        @Test
        void testFilterProductByCategory_NullCategory() {
            GetProductByCategoryService service = new GetProductByCategoryService(productRepository);

            assertThrows(NullPointerException.class, () -> service.filterProductByCategory(null));
        }
    }
}
