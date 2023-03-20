package fpt.edu.shopping.service;

import fpt.edu.shopping.entity.Order;
import fpt.edu.shopping.model.OrderRequest;

import java.util.List;

public interface OrderService {

    Order create(OrderRequest request);

    List<Order> getAll();

    void update(Order order);

    void delete(Long orderId);
}
