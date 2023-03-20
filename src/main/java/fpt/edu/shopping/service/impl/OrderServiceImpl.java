package fpt.edu.shopping.service.impl;

import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.entity.Product;
import fpt.edu.shopping.repository.OrderRepository;
import fpt.edu.shopping.repository.ProductRepository;
import fpt.edu.shopping.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public void create(Order orders) {
        orderRepository.save(orders);
    }
    @Override
    public List<Order> getAll() {
        List<Order> listOrders = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        return listOrders;
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
