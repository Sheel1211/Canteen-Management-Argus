package com.argus.cms.menuManagement.services;

import com.argus.cms.menuManagement.entities.FoodItem;

import java.util.List;

public interface FoodItemService {
    FoodItem addFoodItem(FoodItem foodItem);
    FoodItem getFoodItemById(Long id);
    List<FoodItem> getAllFoodItems();
    void deleteFoodItemById(Long foodItemId);
}
