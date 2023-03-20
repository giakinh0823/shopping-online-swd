package fpt.edu.shopping.service;

import fpt.edu.shopping.entity.Order;

import java.util.List;

public interface OrderService {

    void create(Order orders);

    List<Order> getAll();

    void update(Order order);

    void delete(Long orderId);
}
