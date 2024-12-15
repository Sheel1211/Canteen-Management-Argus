package com.argus.cms.orderManagement.controllers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.OrderDTO;
import com.argus.cms.orderManagement.dtos.OrderResponseDTO;
import com.argus.cms.orderManagement.transformers.OrderTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private OrderTransformer orderTransformer;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderDTO orderDTO) throws RecordNotFoundException {
        OrderResponseDTO createdOrderDTO = orderTransformer.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrderDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable Long orderId) throws RecordNotFoundException {
        OrderResponseDTO orderResponseDTO = orderTransformer.findOrderById(orderId);
        return new ResponseEntity<>(orderResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponseDTO> cancelOrderById(@PathVariable Long orderId) throws RecordNotFoundException{
        OrderResponseDTO orderResponseDTO = orderTransformer.cancelOrderById(orderId);
        return new ResponseEntity<>(orderResponseDTO,HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> viewAllOrdersOfCanteen(){
        List<OrderResponseDTO> orders = orderTransformer.viewALlOrdersOfCanteen();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
