package com.argus.cms.orderManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.entities.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order) throws RecordNotFoundException;

    Order findOrderById(Long orderId) throws RecordNotFoundException;

    Order cancelOrderById(Long orderId) throws RecordNotFoundException;

    List<Order> viewALlOrdersOfCanteen() throws RecordNotFoundException;

}