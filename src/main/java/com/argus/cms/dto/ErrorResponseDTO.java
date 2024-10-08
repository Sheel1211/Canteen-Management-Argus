package com.argus.cms.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorResponseDTO {
    private HttpStatus status;
    private String message;
    private Date timestamp;

    public ErrorResponseDTO(HttpStatus status, String message, Date timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
