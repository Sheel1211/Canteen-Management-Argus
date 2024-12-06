package com.argus.cms.orderManagement.dtos;

import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private UserResponseDTO orderer;
    private Boolean isCancelled;
    private BigDecimal orderAmount;
    private List<OrderItemsResponseDTO> orderItems;
}
