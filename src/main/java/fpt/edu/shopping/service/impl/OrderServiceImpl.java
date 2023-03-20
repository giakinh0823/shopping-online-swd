package fpt.edu.shopping.service.impl;

import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.repository.OrderRepository;
import fpt.edu.shopping.service.OrderService;
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
