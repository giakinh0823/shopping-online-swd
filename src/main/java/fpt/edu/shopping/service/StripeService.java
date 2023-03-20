package fpt.edu.shopping.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import fpt.edu.shopping.config.stripe.StripeConfig;
import fpt.edu.shopping.constant.OrderStatus;
import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.model.PaymentStripeResponse;
import fpt.edu.shopping.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StripeService {
    @Value("${stripe.success_url}")
    private String successUrl;

    @Value("${stripe.cancel_url}")
    private String cancelUrl;

    private final OrderRepository orderRepository;
    private final StripeConfig stripeConfig;

    public StripeService(OrderRepository orderRepository, StripeConfig stripeConfig) {
        this.stripeConfig = stripeConfig;
        this.orderRepository = orderRepository;
    }

    public PaymentStripeResponse createLinkPayment(Long orderId) throws StripeException {
        Stripe.apiKey = stripeConfig.apiKey;

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new RuntimeException("Not found order");
        }
        Order order = optionalOrder.get();

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(successUrl + order.getId())
                .setCancelUrl(cancelUrl + order.getId())
                .addLineItem(SessionCreateParams.LineItem.builder().setQuantity(10L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("VND")
                                .setUnitAmount(order.getTotalPrice())
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                        .builder().setName("Thanh toán đơn hàng " + orderId).build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);
        return PaymentStripeResponse.builder()
                .url(session.getUrl())
                .build();
    }

    public void paymentSuccess(Long orderId) {
        Optional<Order> optional = orderRepository.findById(orderId);
        if(optional.isPresent()){
            Order order = optional.get();
            order.setStatus(OrderStatus.SUCCESS);
            orderRepository.save(order);
        }
    }

    public void paymentCancel(Long orderId) {
        Optional<Order> optional = orderRepository.findById(orderId);
        if(optional.isPresent()){
            Order order = optional.get();
            order.setStatus(OrderStatus.FAILED);
            orderRepository.save(order);
        }
    }
}
