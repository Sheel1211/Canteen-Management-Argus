package com.argus.cms.canteenManagement.dto;

import com.argus.cms.canteenManagement.entities.CanteenUser;
import lombok.Data;

@Data
public class CanteenResponseDTO {
    private Long id;
    private String name;
    private boolean isActive;
    private CanteenUser canteenUser;
}
