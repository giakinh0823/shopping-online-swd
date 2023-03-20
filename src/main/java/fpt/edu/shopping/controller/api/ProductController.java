package fpt.edu.shopping.controller.api;

import fpt.edu.shopping.entity.Product;
import fpt.edu.shopping.model.ProductRequest;
import fpt.edu.shopping.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public void addTour(@RequestBody ProductRequest product) {
        productService.createProduct(product);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("page") int page,
                                                        @RequestParam("limit") int limit
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        return new ResponseEntity<>(productService.findAllProductByPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProduct(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest product) {
        productService.createProduct(product);
        return new ResponseEntity<>("Create product success", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest product) {
        productService.updateProduct(product);
        return new ResponseEntity<>("update product success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("delete product success", HttpStatus.OK);
    }
}
