package com.argus.cms.menuManagement.dtos;


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
public class FoodItemDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private List<Long> categories;
    private Long canteenId;
}