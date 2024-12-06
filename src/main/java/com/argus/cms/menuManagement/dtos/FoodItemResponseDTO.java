package com.argus.cms.menuManagement.dtos;

import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String quantityPerPlate;
    private List<CategoryResponseDTO> categories;
    private CanteenResponseDTO canteen;
}
