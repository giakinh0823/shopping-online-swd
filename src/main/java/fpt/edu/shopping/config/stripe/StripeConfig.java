package fpt.edu.shopping.config.stripe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeConfig {
    @Value("${stripe.api.key}")
    public String apiKey;
}
