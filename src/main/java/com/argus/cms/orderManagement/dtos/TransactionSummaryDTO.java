package com.argus.cms.orderManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSummaryDTO {
    private Long otherUserId;
    private Double netAmount;
    private String otherUserName;
}