package com.argus.cms.menuManagement.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class MenuForMonthRequestDTO {

    private LocalDate currentDate;
    private Long canteenId;
}
