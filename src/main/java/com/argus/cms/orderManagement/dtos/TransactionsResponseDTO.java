package com.argus.cms.orderManagement.dtos;

import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionsResponseDTO {
    private Long id;
    private UserResponseDTO fromEmployee;
    private UserResponseDTO toEmployee;
    private Double amount;
    private String status;
}