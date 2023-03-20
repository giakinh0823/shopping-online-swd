package fpt.edu.shopping.service.impl;

import fpt.edu.shopping.repository.OrderItemRepository;
import fpt.edu.shopping.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderItemImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
}
