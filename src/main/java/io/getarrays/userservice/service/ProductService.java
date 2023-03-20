package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);

    List<Product> findAllProductByPage(Pageable pageable);

    Product findProduct(Long productId);

    void updateProduct(Product product);

    void deleteProduct(Long productId);
}
