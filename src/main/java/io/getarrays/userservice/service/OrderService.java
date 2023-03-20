package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    void create(Order orders);

    List<Order> getAll();

    void update(Order order);

    void delete(Long orderId);
}
