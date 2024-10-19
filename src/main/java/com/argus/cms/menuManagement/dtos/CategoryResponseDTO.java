package com.argus.cms.menuManagement.dtos;


import com.argus.cms.canteenManagement.entities.Canteen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private String type;
    private Canteen canteen;
}