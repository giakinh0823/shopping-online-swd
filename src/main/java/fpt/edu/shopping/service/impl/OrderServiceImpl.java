package fpt.edu.shopping.service.impl;

import fpt.edu.shopping.constant.OrderStatus;
import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.entity.OrderItem;
import fpt.edu.shopping.entity.Product;
import fpt.edu.shopping.model.OrderRequest;
import fpt.edu.shopping.repository.OrderItemRepository;
import fpt.edu.shopping.repository.OrderRepository;
import fpt.edu.shopping.repository.ProductRepository;
import fpt.edu.shopping.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order create(OrderRequest request) {
        if (request == null || request.getOrders() == null) {
            throw new RuntimeException("Not found list order");
        }

        Order order = new Order();
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setStatus(OrderStatus.PENDING);
        order = orderRepository.save(order);

        long totalPrice = 0L;

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRequest.Data data : request.getOrders()) {
            Optional<Product> optional = productRepository.findById(data.getProductId());
            if (optional.isPresent()) {
                Product product = optional.get();
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(data.getQuantity());
                orderItems.add(orderItem);

                totalPrice += product.getPrice();
            }
        }

        orderItems = orderItemRepository.saveAll(orderItems);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        order.setOrderItems(new HashSet<>(orderItems));
        return order;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public void update(Order order) {
        Order orderFind = orderRepository.findById(order.getId()).orElseThrow(() -> new RuntimeException("Not found order"));
        orderFind.setOrderItems(order
                .getOrderItems());
        orderFind.setStatus(order.getStatus());
        orderFind.setTotalPrice(order.getTotalPrice());
        orderFind.setCreatedAt(order.getCreatedAt());
        orderFind.setUpdatedAt(order.getUpdatedAt());
        orderRepository.save(orderFind);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
