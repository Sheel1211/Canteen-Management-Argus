package com.argus.cms.menuManagement.validation;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.canteenManagement.repositories.CanteenUserRepository;
import com.argus.cms.constants.ErrorLevel;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.repositories.FoodItemRepository;
import com.argus.cms.menuManagement.services.FoodItemService;
import com.argus.cms.security.CustomUserDetails;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import com.argus.cms.validations.ValidationResultDTO;
import com.argus.cms.validations.ValidationTypeKey;
import com.argus.cms.validations.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

@Component
@AllArgsConstructor
public class FoodItemValidator {
    private FoodItemRepository foodItemRepository;
    private CanteenUserRepository canteenUserRepository;
    private UserRepository userRepository;


    public void validateCreateFoodItems(FoodItem foodItem, FoodItemService foodItemService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateFoodItems(ValidationTypeKey.CREATE_VALIDATION, foodItemService,foodItem);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
//            LOGGER.error("{}{}", LoggerConstants.DATA_VALIDATION_EXCEPTION, HolidayValidator.class.getSimpleName());
            throw new DataValidationErrorException(errors);

        }
    }

    public void validateUpdateFoodItems(FoodItem foodItem, FoodItemService foodItemService) throws InvalidParameterException, RecordNotFoundException, DataValidationErrorException {
        List<ValidationResultDTO> errors = validateFoodItems(ValidationTypeKey.UPDATE_VALIDATION, foodItemService, foodItem);
        if (ValidationUtils.containsErrorLevel(errors, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(errors);
        }
    }

    public List<ValidationResultDTO> validateFoodItems(ValidationTypeKey validationTypeKey, FoodItemService foodItemService,
                                                   FoodItem foodItem) throws InvalidParameterException {

        List<ValidationResultDTO> errors = ValidationUtils.validateBeanValidation(foodItem, validationTypeKey);
        //check if food item with same name in the same canteen already exists
        validateSameFoodItemNameExists(foodItem, errors);
        validateIfCanteenManagerCanAddOrUpdate(foodItem, foodItemService ,errors);
        return errors;
    }

    public void validateSameFoodItemNameExists(FoodItem foodItem, List<ValidationResultDTO> errors) {
        FoodItem toSavefoodItem = foodItemRepository.findByNameAndCanteen(foodItem.getName(),foodItem.getCanteen()).orElse(null);
        if (toSavefoodItem != null) {
            errors.add(new ValidationResultDTO("FoodItemName", ErrorLevel.ERROR, "FoodItemName Already Exists in your canteen! Please choose another foodItemName!"));
        }
    }


    public void validateIfCanteenManagerCanAddOrUpdate(FoodItem foodItem,FoodItemService foodItemService, List<ValidationResultDTO> errors) {

        Canteen canteen = foodItem.getCanteen();

        CustomUserDetails currUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currUser= userRepository.getById(currUserDetails.getUserId());

        CanteenUser canteenUser = canteenUserRepository.findByUserAndCanteenAndIsActiveTrue(currUser,canteen).orElse(null);
        if(canteenUser != null) return;

        errors.add(new ValidationResultDTO("FoodItemName", ErrorLevel.ERROR, "Cannot add or updated food of another canteen you are not assigned to!"));

    }

}
