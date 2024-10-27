package com.argus.cms.validations;

import com.argus.cms.constants.ErrorLevel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationUtils {
    public static List<ValidationResultDTO> getValidationResultDtos(Set<ConstraintViolation<Object>> constraintViolations){
        List<ValidationResultDTO> validationResultDtos = new ArrayList<>();
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            ValidationResultDTO validationResultDto = new ValidationResultDTO();
            validationResultDto.setMessage(constraintViolation.getMessage());
            validationResultDto.setLevel(ErrorLevel.ERROR);
            validationResultDto.setElement(constraintViolation.getPropertyPath().toString());
            validationResultDtos.add(validationResultDto);
        }
        return validationResultDtos;
    }

    public static List<ValidationResultDTO> validateBeanValidation(Object object, ValidationTypeKey validationTypeKey){
        Validator validator = ValidatorProvider.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        return ValidationUtils.getValidationResultDtos(constraintViolations);
    }

    public static boolean containsErrorLevel(List<ValidationResultDTO> errors, ErrorLevel level) {

        for (ValidationResultDTO validationResult : errors) {
            if (validationResult.getLevel() != null && validationResult.getLevel().equals(level)) {
                return true;
            }
        }
        return false;
    }

}
