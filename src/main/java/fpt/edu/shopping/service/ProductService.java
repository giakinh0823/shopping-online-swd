package fpt.edu.shopping.service;

import fpt.edu.shopping.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);

    List<Product> findAllProductByPage(Pageable pageable);

    Product findProduct(Long productId);

    void updateProduct(Product product);

    void deleteProduct(Long productId);
}
