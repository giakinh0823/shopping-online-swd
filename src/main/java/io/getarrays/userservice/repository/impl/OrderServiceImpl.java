package io.getarrays.userservice.repository.impl;

import io.getarrays.userservice.domain.Order;
import io.getarrays.userservice.repository.OrderRepository;
import io.getarrays.userservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public void create(Order orders) {

    }
    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Long orderId) {

    }
}
