package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.ConcurrentModificationException;
import com.argus.cms.exceptions.EntityNotFoundException;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.repositories.FoodItemRepository;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
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

    @Override
    @Transactional
    public FoodItem updateFoodItem(Long foodItemId,FoodItem foodItemReq) {

        try{
            FoodItem foodItem  = this.getFoodItemById(foodItemId);
            foodItem.setPrice(foodItemReq.getPrice());
            foodItem.setName(foodItem.getName());
            foodItem.setCategories(foodItem.getCategories());
            foodItem.setDescription(foodItem.getDescription());
            foodItem.setQuantity(foodItem.getQuantity());
            return foodItem;
        }catch (OptimisticEntityLockException e) {
            throw new ConcurrentModificationException("Failed to update food item due to concurrent modification. Please try again.");
        }
    }
}