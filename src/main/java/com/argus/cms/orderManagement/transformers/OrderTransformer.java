package com.argus.cms.orderManagement.transformers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.OrderDTO;
import com.argus.cms.orderManagement.dtos.OrderResponseDTO;
import com.argus.cms.orderManagement.entities.Order;
import com.argus.cms.orderManagement.mappers.OrderMapper;
import com.argus.cms.orderManagement.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderTransformer {

    private OrderService orderService;
    private OrderMapper orderMapper;

    public OrderResponseDTO createOrder(OrderDTO orderDTO){
        Order order = orderMapper.toEntity(orderDTO);
        Order createdOrder = orderService.createOrder(order);
        return orderMapper.toResponseDTO(createdOrder);
    }

    public OrderResponseDTO findOrderById(Long orderId) throws RecordNotFoundException {
        Order order = orderService.findOrderById(orderId);
        return null;
    }

    public OrderResponseDTO cancelOrderById(Long orderId) throws RecordNotFoundException{
        Order order = orderService.cancelOrderById(orderId);
        return null;
    }
}
