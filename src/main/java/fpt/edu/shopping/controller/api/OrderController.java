package fpt.edu.shopping.controller.api;

import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.model.OrderRequest;
import fpt.edu.shopping.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(OrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }
}
