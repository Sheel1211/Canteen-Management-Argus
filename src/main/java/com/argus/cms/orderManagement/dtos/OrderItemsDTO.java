package com.argus.cms.orderManagement.dtos;

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
public class OrderItemsDTO {
    private Long receiverId;
    private Long batchId;
    private BigDecimal amount;
    private List<OrderFoodCommonDTO> orderedFoodItems;
}
