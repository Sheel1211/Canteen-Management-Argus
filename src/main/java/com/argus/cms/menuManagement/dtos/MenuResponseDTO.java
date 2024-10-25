package com.argus.cms.menuManagement.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MenuResponseDTO {

    private String name;
    private LocalDate date_of_menu;
    private Long categoryId;
    private String canteenName;
//    private LocalDateTime createdAt;
    private List<FoodItemResponseDTO> foodItems;
}
