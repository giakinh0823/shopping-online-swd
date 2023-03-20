package fpt.edu.shopping.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStripeResponse {
    private String url;
}
