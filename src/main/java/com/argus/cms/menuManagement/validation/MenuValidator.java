package com.argus.cms.menuManagement.validation;

import com.argus.cms.constants.ErrorLevel;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Menu;

import com.argus.cms.menuManagement.repositories.MenuRepository;
import com.argus.cms.menuManagement.services.MenuService;

import com.argus.cms.validations.ValidationResultDTO;
import com.argus.cms.validations.ValidationTypeKey;
import com.argus.cms.validations.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

@Component
@AllArgsConstructor
public class MenuValidator {
    private MenuRepository menuRepository;

    public void validateCreateMenu(Menu menu, MenuService menuService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateMenu(ValidationTypeKey.CREATE_VALIDATION, menuService,menu);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(errors);

        }
    }

//    public void validateUpdateMenu(Menu menu, MenuService menuService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
//        List<ValidationResultDTO> errors = validateMenu(ValidationTypeKey.UPDATE_VALIDATION, menuService, menu);
//        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
//            throw new DataValidationErrorException(errors);
//        }
//    }

    public List<ValidationResultDTO> validateMenu(ValidationTypeKey validationTypeKey, MenuService menuService,
                                                   Menu menu) throws InvalidParameterException {
        // validate bean validations
        List<ValidationResultDTO> errors = ValidationUtils.validateBeanValidation(menu, validationTypeKey);

        // validate common for create and update
        validateSameMenuNameExists(menu, errors);
        return errors;
    }

    public void validateSameMenuNameExists(Menu menu, List<ValidationResultDTO> errors) {
        Menu toSavemenu = menuRepository.findByNameAndCanteen(menu.getName(),menu.getCanteen()).orElse(null);
        if (toSavemenu != null) {
            errors.add(new ValidationResultDTO("name", ErrorLevel.ERROR, "Menu Name Already Exists in your canteen! Please choose another Menu Name!"));
        }
    }

}
