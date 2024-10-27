package com.argus.cms.exceptions;

import com.argus.cms.validations.ValidationResultDTO;

import java.util.List;

public class DataValidationErrorException extends  Exception {
    private final List<ValidationResultDTO> errors;

    public DataValidationErrorException(List<ValidationResultDTO> errors) {
        super("Data validation failed");
        this.errors = errors;
    }

    public List<ValidationResultDTO> getErrors() {
        return errors;
    }
}
