package io.getarrays.userservice.service.impl;

import io.getarrays.userservice.repository.OrderItemRepository;
import io.getarrays.userservice.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderItemImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
}
