package com.argus.cms.userManagement.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequestDTO {

//    @NotBlank(message = "Username cannot be blank")
//    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String userName;

//    @NotBlank(message = "Password cannot be blank")
//    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    private String role;
}