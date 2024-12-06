package com.argus.cms.orderManagement.services;

import com.argus.cms.orderManagement.entities.OrderItems;
import com.argus.cms.orderManagement.repositories.OrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService{

    private OrderItemsRepository orderItemRepository;

    public OrderItems createOrderItem(OrderItems orderItem) {
        return orderItemRepository.save(orderItem);
    }
}