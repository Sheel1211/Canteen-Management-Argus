package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.ConcurrentModificationException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.FoodItem;

import java.util.List;

public interface FoodItemService {
    FoodItem addFoodItem(FoodItem foodItem) throws RecordNotFoundException, DataValidationErrorException;
    FoodItem getFoodItemById(Long id) throws RecordNotFoundException;
    List<FoodItem> getAllFoodItems();
    void deleteFoodItemById(Long foodItemId) throws RecordNotFoundException;
    FoodItem updateFoodItem(Long foodItemId, FoodItem foodItem) throws RecordNotFoundException, DataValidationErrorException, ConcurrentModificationException;
    List<FoodItem> getFoodItemsByCanteenId(Long canteenId) throws RecordNotFoundException;
    List<FoodItem> getFoodItemsByCanteenIdAndCategory(Long canteenId, Long categoryId) throws RecordNotFoundException;
}
