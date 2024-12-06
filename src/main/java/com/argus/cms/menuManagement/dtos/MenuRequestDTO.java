package com.argus.cms.menuManagement.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuRequestDTO {
    private String name;
    private LocalDate dateOfMenu;
    private Long categoryId;
    private Long canteenId;
    private List<MenuFoodItemRequestDTO> menuFoodItemList;
}