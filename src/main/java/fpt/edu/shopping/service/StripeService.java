package fpt.edu.shopping.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import fpt.edu.shopping.config.stripe.StripeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StripeService {
    private StripeConfig stripeConfig;

    @Value("${stripe.success_url}")
    private String successUrl;

    @Value("${stripe.cancel_url}")
    private String cancelUrl;

    public StripeService(StripeConfig stripeConfig) {
        this.stripeConfig = stripeConfig;
    }

    public String createLinkPayment(Long orderId) throws StripeException {
        Stripe.apiKey = stripeConfig.apiKey;

        if (orderId == null) {
            throw new RuntimeException("OrderId not found");
        }
        ProductCreateParams paramsProduct =
                ProductCreateParams.builder()
                        .setName("Macbook M2 2023")
                        .setCaption("Macbook apple pro max free 50% discount")
                        .setDescription("Macbook apple pro max free 50% discount")
                        .build();

        PriceCreateParams paramsPrice =
                PriceCreateParams
                        .builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setProduct(paramsProduct.getId())
                        .build();

        Price price = Price.create(paramsPrice);

        PaymentLinkCreateParams paramsLinks =
                PaymentLinkCreateParams
                        .builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem
                                        .builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion
                                        .builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect
                                                        .builder()
                                                        .setUrl("https://gurucoding.org/" + orderId)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
        ;


        PaymentLink paymentLink = PaymentLink.create(paramsLinks);
        return paymentLink.getUrl();
    }
}
