package com.argus.cms.orderManagement.dtos;

import com.argus.cms.menuManagement.dtos.FoodItemResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderFoodCommonResponseDTO {
    private Integer id;
    private FoodItemResponseDTO foodItem;
    private Integer quantity;
}
