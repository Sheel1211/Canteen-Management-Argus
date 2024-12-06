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

    private Long id;
    private String name;
    private LocalDate dateOfMenu;
    private CategoryResponseDTO category;
    private String canteenName;
    private List<MenuFoodItemResponseDTO> menuFoodItemList;
}
