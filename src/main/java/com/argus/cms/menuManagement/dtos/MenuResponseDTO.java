package com.argus.cms.menuManagement.dtos;

import com.argus.cms.menuManagement.entities.FoodItem;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuResponseDTO {

    private String name;
    private LocalDate date_of_menu;
    private Long categoryId;
    private String canteenName;
    private LocalDateTime createdAt;
    private List<FoodItem> foodItems;
}
