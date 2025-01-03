package com.argus.cms.canteenManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CanteenResponseDTO {
    private Long id;
    private String name;
    private boolean isActive;
//    private List<String> canteenManagers;
}