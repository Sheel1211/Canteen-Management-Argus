package com.argus.cms.orderManagement.dtos;

import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderItemsResponseDTO {
    private Long id;
    private UserResponseDTO receiver;
    private String batch;
    private String status;
    private BigDecimal amount;
    private List<OrderFoodCommonResponseDTO> orderedFoodItems;
}
