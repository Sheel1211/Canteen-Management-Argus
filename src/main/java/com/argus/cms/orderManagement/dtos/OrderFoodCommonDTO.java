package com.argus.cms.orderManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFoodCommonDTO {
    private Long foodItemId;
    private Integer quantity;
}
