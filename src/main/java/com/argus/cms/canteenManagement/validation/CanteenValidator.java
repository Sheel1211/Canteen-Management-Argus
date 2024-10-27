package com.argus.cms.canteenManagement.validation;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.repositories.CanteenRepository;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.constants.ErrorLevel;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.validations.ValidationResultDTO;
import com.argus.cms.validations.ValidationTypeKey;
import com.argus.cms.validations.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

@Component
@AllArgsConstructor
public class CanteenValidator {
    private CanteenRepository canteenRepository;

    public void validateCreateCanteen(Canteen canteen, CanteenService canteenService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateCanteen(ValidationTypeKey.CREATE_VALIDATION, canteenService, canteen);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
//            LOGGER.error("{}{}", LoggerConstants.DATA_VALIDATION_EXCEPTION, HolidayValidator.class.getSimpleName());
            throw new DataValidationErrorException(errors);

        }
    }

    public void validateUpdateCanteen(Canteen canteen, CanteenService canteenService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateCanteen(ValidationTypeKey.UPDATE_VALIDATION, canteenService, canteen);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(errors);
        }
    }

    public List<ValidationResultDTO> validateCanteen(ValidationTypeKey validationTypeKey, CanteenService canteenService,
                                                   Canteen canteen) throws InvalidParameterException {
        List<ValidationResultDTO> errors = ValidationUtils.validateBeanValidation(canteen, validationTypeKey);

        return errors;
    }
}
