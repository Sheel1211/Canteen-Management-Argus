package com.argus.cms.userManagement.roles.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {

    private Long id;

    @NotBlank(message = "Role name cannot be blank")
    private String name;
}