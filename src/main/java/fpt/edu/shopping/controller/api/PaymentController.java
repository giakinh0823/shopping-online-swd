package fpt.edu.shopping.controller.api;

import com.stripe.exception.StripeException;
import fpt.edu.shopping.model.PaymentStripeResponse;
import fpt.edu.shopping.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private final StripeService stripeService;

    @GetMapping("/payment/bill/{orderId}")
    public ResponseEntity<PaymentStripeResponse> createPaymentLink(@PathVariable("orderId") Long orderId) throws StripeException {
        return ResponseEntity.ok(stripeService.createLinkPayment(orderId));
    }
}
