package fpt.edu.shopping.controller.api;

import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.entity.Product;
import fpt.edu.shopping.service.OrderService;
import fpt.edu.shopping.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/list")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        orderService.create(order);
        return new ResponseEntity<>("Create order success", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        orderService.update(order);
        return new ResponseEntity<>("update order success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>("delete order success", HttpStatus.OK);
    }
}
