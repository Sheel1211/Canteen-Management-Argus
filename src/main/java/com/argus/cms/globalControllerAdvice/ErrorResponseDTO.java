package com.argus.cms.globalControllerAdvice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
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
