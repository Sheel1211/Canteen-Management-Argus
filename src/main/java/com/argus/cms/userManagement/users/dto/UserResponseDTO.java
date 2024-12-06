package com.argus.cms.userManagement.users.dto;

import com.argus.cms.userManagement.roles.entities.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String userName;
    private LocalDateTime createdAt;
    private Integer balance = 0;
    private Boolean isActive;
    private Roles role;
}
