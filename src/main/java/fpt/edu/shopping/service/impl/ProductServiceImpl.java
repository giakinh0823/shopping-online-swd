package fpt.edu.shopping.service.impl;

import fpt.edu.shopping.entity.Product;
import fpt.edu.shopping.repository.ProductRepository;
import fpt.edu.shopping.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProductByPage(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable).getContent();
        return new ArrayList<>(products);
    }

    @Override
    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Not found product"));
    }

    @Override
    public void updateProduct(Product product) {
        Product productFind = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Not found product"));
        productFind.setName(product
                .getName());
        productFind.setQuantity(product.getQuantity());
        productFind.setType(product.getType());
        productFind.setDescription(product.getDescription());
        productFind.setPrice(product.getPrice());
        productRepository.save(productFind);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
