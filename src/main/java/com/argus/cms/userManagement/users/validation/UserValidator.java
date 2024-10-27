package com.argus.cms.userManagement.users.validation;

import com.argus.cms.constants.ErrorLevel;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import com.argus.cms.userManagement.users.services.UserService;
import com.argus.cms.validations.ValidationResultDTO;
import com.argus.cms.validations.ValidationTypeKey;
import com.argus.cms.validations.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

@Component
@AllArgsConstructor
public class UserValidator {
    private UserRepository userRepository;

    public void validateCreateUsers(Users user, UserService userService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateUsers(ValidationTypeKey.CREATE_VALIDATION, userService, user);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
//            LOGGER.error("{}{}", LoggerConstants.DATA_VALIDATION_EXCEPTION, HolidayValidator.class.getSimpleName());
            throw new DataValidationErrorException(errors);

        }
    }

    public void validateUpdateUsers(Users user, UserService userService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateUsers(ValidationTypeKey.UPDATE_VALIDATION, userService, user);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(errors);
        }
    }

    public List<ValidationResultDTO> validateUsers(ValidationTypeKey validationTypeKey, UserService userService,
                                                   Users user) throws InvalidParameterException {
        // validate bean validations
        List<ValidationResultDTO> errors = ValidationUtils.validateBeanValidation(user, validationTypeKey);

        // validate common for create and update
        validateSameUserNameExists(user, errors);

        switch (validationTypeKey) {
            case CREATE_VALIDATION -> {
//                validateIfEntityCodeAlreadyExists(entityBean, entityService ,errors);
            }
            case UPDATE_VALIDATION -> {
//                validateIfEntityCodeDoesNotExists(entityBean, entityService ,errors);
//                validateIsEntityUpdateAllowed(entityBean, entityService, errors);
            }
        }
        return errors;
    }

    public void validateSameUserNameExists(Users user, List<ValidationResultDTO> errors) {
        Users toSaveUser = userRepository.findByUserName(user.getUserName()).orElse(null);
        if (toSaveUser != null) {
            errors.add(new ValidationResultDTO("username", ErrorLevel.ERROR, "Username Already Exists! Please choose another username!"));
        }
    }

}
