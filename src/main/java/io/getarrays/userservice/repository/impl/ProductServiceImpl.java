package io.getarrays.userservice.repository.impl;

import io.getarrays.userservice.domain.Product;
import io.getarrays.userservice.repository.ProductRepository;
import io.getarrays.userservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.transaction.Transactional
@Slf4j
@Service
public class ProductServiceImpl  implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProductByPage(Pageable pageable) {
        List<Product> listProduct = new ArrayList<>();
        List<Product> products = productRepository.findAll(pageable).getContent();
        for (Product itemL : products) {
            listProduct.add(itemL);
        }
        return listProduct;
    }

    @Override
    public Product findProduct(Long productId) {
        Product productFind = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Not found product"));
        return productFind;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
