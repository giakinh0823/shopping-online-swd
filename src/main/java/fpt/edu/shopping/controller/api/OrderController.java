package fpt.edu.shopping.controller.api;

import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.model.OrderRequest;
import fpt.edu.shopping.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(OrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
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
