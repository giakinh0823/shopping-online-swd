package fpt.edu.shopping.controller.api;

import com.stripe.exception.StripeException;
import fpt.edu.shopping.model.PaymentStripeResponse;
import fpt.edu.shopping.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private final StripeService stripeService;

    @GetMapping("/payment/bill/{orderId}")
    public ResponseEntity<PaymentStripeResponse> createPaymentLink(@PathVariable("orderId") Long orderId) throws StripeException {
        return ResponseEntity.ok(stripeService.createLinkPayment(orderId));
    }

    @GetMapping("/payment/bill/success/{orderId}")
    public ResponseEntity<String> paymentSuccess(@PathVariable("orderId") Long orderId) {
        stripeService.paymentSuccess(orderId);
        return ResponseEntity.ok("Payment Success order: "+ orderId);
    }

    @GetMapping("/payment/bill/cancel/{orderId}")
    public ResponseEntity<String> paymentCancel(@PathVariable("orderId") Long orderId) {
        stripeService.paymentCancel(orderId);
        return ResponseEntity.ok("Payment Error order: " + orderId);
    }
}
