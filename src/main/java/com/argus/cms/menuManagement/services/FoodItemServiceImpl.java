package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.EntityNotFoundException;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.repositories.FoodItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService{

    private final FoodItemRepository foodItemRepository;

    @Override
    @Transactional
    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    @Transactional(readOnly = true)
    public FoodItem getFoodItemById(Long foodItemId) {
        return foodItemRepository.findById(foodItemId).orElseThrow(()-> new EntityNotFoundException("Food Item Not Found With Id " + foodItemId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteFoodItemById(Long foodItemId) {
        FoodItem foodItem = this.getFoodItemById(foodItemId);
        if(foodItem.getIsDeleted()){
            throw new EntityNotFoundException("Food Item doesn't exist with id " + foodItemId);
        }
        foodItem.setIsDeleted(true);
    }
}