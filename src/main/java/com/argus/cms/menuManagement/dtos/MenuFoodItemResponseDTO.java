package com.argus.cms.menuManagement.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MenuFoodItemResponseDTO {
    private FoodItemResponseDTO foodItem;
    private Long quantity;
}
