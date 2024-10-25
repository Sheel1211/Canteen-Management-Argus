package com.argus.cms.menuManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuRequestDTO {

    private String name;
    private LocalDate date_of_menu;
    private Long categoryId;
    private Long canteenId;
    private List<Long> foodItemListIds;

}