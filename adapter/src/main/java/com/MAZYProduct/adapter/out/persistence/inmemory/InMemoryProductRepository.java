package com.MAZYProduct.adapter.out.persistence.inmemory;

import com.MAZYProduct.application.port.out.persistence.ProductRepository;
import com.MAZYProduct.product.Category;
import com.MAZYProduct.product.Product;
import com.MAZYProduct.product.ProductId;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ConditionalOnProperty(name = "persistence", havingValue = "inmemory", matchIfMissing = true)
@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<ProductId, Product> products = new ConcurrentHashMap<>();

    private int idSequence = 1;

    public InMemoryProductRepository() {
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(new ProductId(idSequence));
            idSequence += 1;
        }
        products.put(product.getId(), product);
    }

    @Override
    public void deleteById(ProductId id) {
        products.remove(id);
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<Product> filterProductByCategory(Category category) {
        return products.values().stream()
                .filter(product -> matchesQuery(product, category))
                .toList();
    }

    private boolean matchesQuery(Product product, Category category) {
        return product.getCategory() == category;
    }
}
