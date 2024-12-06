package com.argus.cms.orderManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("Order is pending"),
    APPROVED("Order has been approved"),
    REJECTED("Order has been rejected"),
    COMPLETED("Order is completed");

    private String description;
}