package com.argus.cms.menuManagement.dtos;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.menuManagement.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    private List<Category> categories;
    private Canteen canteen;
}
