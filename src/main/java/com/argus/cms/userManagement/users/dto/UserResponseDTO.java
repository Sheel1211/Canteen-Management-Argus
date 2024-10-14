package com.argus.cms.userManagement.users.dto;

import com.argus.cms.userManagement.roles.entities.Roles;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;
    private LocalDateTime createdAt;
    private Integer balance = 0;
    private Boolean isActive;
    private Set<Roles> roles;
}
