package fpt.edu.shopping.controller.api;

import fpt.edu.shopping.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/paypal")
@AllArgsConstructor
public class PayPalController {
    private final OrderService orderService;

}
