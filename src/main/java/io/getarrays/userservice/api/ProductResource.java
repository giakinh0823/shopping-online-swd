package io.getarrays.userservice.api;

import io.getarrays.userservice.domain.Product;
import io.getarrays.userservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductResource {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public void addTour(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("page") int page,
                                                        @RequestParam("limit") int limit
    ){
        Pageable pageable = PageRequest.of(page, limit);
        return new ResponseEntity<>(productService.findAllProductByPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProduct(id), HttpStatus.OK);
    }
    @PutMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return new ResponseEntity<>("Create product success", HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return new ResponseEntity<>("update product success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("delete product success", HttpStatus.OK);
    }
}
