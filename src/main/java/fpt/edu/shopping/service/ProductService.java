package fpt.edu.shopping.service;

import fpt.edu.shopping.entity.Product;
import fpt.edu.shopping.model.ProductRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest product);

    List<Product> findAllProductByPage(Pageable pageable);

    Product findProduct(Long productId);

    void updateProduct(ProductRequest product);

    void deleteProduct(Long productId);
}
