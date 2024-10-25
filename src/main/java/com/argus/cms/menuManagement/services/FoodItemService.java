package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.FoodItemDTO;
import com.argus.cms.menuManagement.entities.FoodItem;

import java.util.List;

public interface FoodItemService {
    FoodItem addFoodItem(FoodItem foodItem);
    FoodItem getFoodItemById(Long id) throws RecordNotFoundException;
    List<FoodItem> getAllFoodItems();
    void deleteFoodItemById(Long foodItemId) throws RecordNotFoundException;
    FoodItem updateFoodItem(Long foodItemId, FoodItem foodItem) throws RecordNotFoundException;
}
