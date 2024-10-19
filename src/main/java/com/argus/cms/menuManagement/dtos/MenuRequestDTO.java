package com.argus.cms.menuManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDTO {

    private String name;
    private LocalDate date_of_menu;
    private Long categoryId;
    private Long canteenId;
    private List<Long> foodItemListIds;

}