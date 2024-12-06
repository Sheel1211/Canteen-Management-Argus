package com.argus.cms.canteenManagement.validation;

import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.canteenManagement.repositories.CanteenUserRepository;
import com.argus.cms.canteenManagement.services.CanteenUserService;
import com.argus.cms.constants.ErrorLevel;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.validations.ValidationResultDTO;
import com.argus.cms.validations.ValidationTypeKey;
import com.argus.cms.validations.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

@Component
@AllArgsConstructor
public class CanteenUserValidator {
    private CanteenUserRepository canteenUserRepository;

    public void validateCreateCanteenManager(CanteenUser canteenUser, CanteenUserService canteenUserService) throws InvalidParameterException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateCanteenManager(ValidationTypeKey.CREATE_VALIDATION, canteenUserService, canteenUser);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
//            LOGGER.error("{}{}", LoggerConstants.DATA_VALIDATION_EXCEPTION, HolidayValidator.class.getSimpleName());
            throw new DataValidationErrorException(errors);
        }
    }

    public void validateUpdateCanteenManager(CanteenUser canteenUser, CanteenUserService canteenUserService) throws InvalidParameterException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateCanteenManager(ValidationTypeKey.UPDATE_VALIDATION, canteenUserService, canteenUser);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(errors);
        }
    }

    public List<ValidationResultDTO> validateCanteenManager(ValidationTypeKey validationTypeKey, CanteenUserService canteenUserService,
                                                            CanteenUser canteenUser) throws InvalidParameterException {
        List<ValidationResultDTO> errors = ValidationUtils.validateBeanValidation(canteenUser, validationTypeKey);

        validateIsUserRoleCanteenManager(canteenUser, errors);
        validateIsEntryAlreadyExisting(canteenUser, errors);
        return errors;
    }

    public void validateIsUserRoleCanteenManager(CanteenUser canteenUser, List<ValidationResultDTO> errors) {
        boolean IsUserRoleCanteenManager = canteenUser.getUser().getRole().getName().equals("ROLE_CANTEEN_MANAGER");
        if (!IsUserRoleCanteenManager) {
            errors.add(new ValidationResultDTO("Role", ErrorLevel.ERROR, "You are not a Canteen Manager!"));
        }
    }

    public void validateIsEntryAlreadyExisting(CanteenUser canteenUser, List<ValidationResultDTO> errors) {
        CanteenUser isEntryAlreadyExisting = canteenUserRepository.findByUserAndCanteen(canteenUser.getUser(), canteenUser.getCanteen()).orElse(null);
        if (isEntryAlreadyExisting != null) {
            errors.add(new ValidationResultDTO("Role", ErrorLevel.ERROR, "You have Already applied for the same canteen!"));
        }
    }

    public void validateIsManagerAlreadyActiveForCanteen(CanteenUser canteenUser, List<ValidationResultDTO> errors) {
        CanteenUser alreadyExistingManager = canteenUserRepository.findByUserAndIsActiveTrue(canteenUser.getUser()).orElse(null);
        if (alreadyExistingManager != null) {
            errors.add(new ValidationResultDTO("Role", ErrorLevel.ERROR, "You are already active for some other canteen!"));
        }
    }
}

