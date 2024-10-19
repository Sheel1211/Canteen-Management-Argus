package com.argus.cms.menuManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private List<Long> categories;
    private Long canteenId;
}