package com.argus.cms.orderManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.entities.Order;

public interface OrderService {
    Order createOrder(Order order);

    Order findOrderById(Long orderId) throws RecordNotFoundException;

    Order cancelOrderById(Long orderId) throws RecordNotFoundException;
}