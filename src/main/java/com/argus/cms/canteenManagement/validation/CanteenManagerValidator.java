package com.argus.cms.canteenManagement.validation;

import com.argus.cms.canteenManagement.entities.CanteenManager;
import com.argus.cms.canteenManagement.repositories.CanteenManagerRepository;
import com.argus.cms.canteenManagement.services.CanteenManagerService;
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
public class CanteenManagerValidator {
    private CanteenManagerRepository canteenManagerRepository;

    public void validateCreateCanteenManager(CanteenManager canteenManager, CanteenManagerService canteenManagerService) throws InvalidParameterException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateCanteenManager(ValidationTypeKey.CREATE_VALIDATION, canteenManagerService, canteenManager);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
//            LOGGER.error("{}{}", LoggerConstants.DATA_VALIDATION_EXCEPTION, HolidayValidator.class.getSimpleName());
            throw new DataValidationErrorException(errors);
        }
    }

    public void validateUpdateCanteenManager(CanteenManager canteenManager, CanteenManagerService canteenManagerService) throws InvalidParameterException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateCanteenManager(ValidationTypeKey.UPDATE_VALIDATION, canteenManagerService, canteenManager);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(errors);
        }
    }

    public List<ValidationResultDTO> validateCanteenManager(ValidationTypeKey validationTypeKey, CanteenManagerService canteenManagerService,
                                                            CanteenManager canteenManager) throws InvalidParameterException {
        List<ValidationResultDTO> errors = ValidationUtils.validateBeanValidation(canteenManager, validationTypeKey);

        validateIsUserRoleCanteenManager(canteenManager, errors);
        validateIsEntryAlreadyExisting(canteenManager, errors);
        return errors;
    }

    public void validateIsUserRoleCanteenManager(CanteenManager canteenManager, List<ValidationResultDTO> errors) {
        boolean IsUserRoleCanteenManager = canteenManager.getManager().getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_CANTEEN_MANAGER"));
        if (!IsUserRoleCanteenManager) {
            errors.add(new ValidationResultDTO("Role", ErrorLevel.ERROR, "You are not a Canteen Manager!"));
        }
    }

    public void validateIsEntryAlreadyExisting(CanteenManager canteenManager, List<ValidationResultDTO> errors) {
        CanteenManager isEntryAlreadyExisting = canteenManagerRepository.findByManagerAndCanteen(canteenManager.getManager(), canteenManager.getCanteen()).orElse(null);
        if (isEntryAlreadyExisting != null) {
            errors.add(new ValidationResultDTO("Role", ErrorLevel.ERROR, "You have Already applied for the same canteen!"));
        }
    }

    public void validateIsManagerAlreadyActiveForCanteen(CanteenManager canteenManager, List<ValidationResultDTO> errors) {
        CanteenManager alreadyExistingManager = canteenManagerRepository.findByManagerAndIsActiveTrue(canteenManager.getManager()).orElse(null);
        if (alreadyExistingManager != null) {
            errors.add(new ValidationResultDTO("Role", ErrorLevel.ERROR, "You are already active for some other canteen!"));
        }
    }
}

