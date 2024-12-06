package com.argus.cms.menuManagement.dtos;


import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String type;
    private CanteenResponseDTO canteen;
}