package com.argus.cms.validations;

import com.argus.cms.constants.ErrorLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationResultDTO {
    private static final long serialVersionUID = 1L;

    private String element;

    private ErrorLevel level;

    private String message;

    private Object refId;

    public ValidationResultDTO() {
        this.level = ErrorLevel.OK;
    }

    public ValidationResultDTO(String element) {
        this();
        this.element = element;
    }

    public ValidationResultDTO(String element, ErrorLevel level, String message) {
        this.element = element;
        this.level = level;
        this.message = message;
    }
}
