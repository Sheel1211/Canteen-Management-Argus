package com.argus.cms.menuManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.ConcurrentModificationException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.repositories.FoodItemRepository;
import com.argus.cms.menuManagement.validation.FoodItemValidator;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService{

    private final FoodItemRepository foodItemRepository;
    private final FoodItemValidator foodItemValidator;
    private final CanteenService canteenService;
    private final CategoryService categoryService;
    @Override
    @Transactional
    public FoodItem addFoodItem(FoodItem foodItem) throws RecordNotFoundException, DataValidationErrorException {
        foodItemValidator.validateCreateFoodItems(foodItem,this);
        return foodItemRepository.save(foodItem);
    }

    @Override
    @Transactional(readOnly = true)
    public FoodItem getFoodItemById(Long foodItemId) throws RecordNotFoundException {

        return foodItemRepository.findById(foodItemId).orElseThrow(()-> new RecordNotFoundException("Food Item Not Found With Id " + foodItemId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteFoodItemById(Long foodItemId) throws RecordNotFoundException {
        FoodItem foodItem = this.getFoodItemById(foodItemId);
        if(foodItem.getIsDeleted()){
            throw new RecordNotFoundException("Food Item doesn't exist with id " + foodItemId);
        }
        foodItem.setIsDeleted(true);
    }

    @Override
    public List<FoodItem> getFoodItemsByCanteenIdAndCategory(Long canteenId, Long categoryId) throws RecordNotFoundException {
        Canteen canteen = canteenService.getCanteenById(canteenId);
        Category category = categoryService.getCategoryById(categoryId);
        return foodItemRepository.findByCanteenAndCategories(canteen,category);
    }

    @Override
    public List<FoodItem> getFoodItemsByCanteenId(Long canteenId) throws RecordNotFoundException {
        Canteen canteen = canteenService.getCanteenById(canteenId);
        return foodItemRepository.findByCanteen(canteen);
    }

    @Override
    @Transactional
    public FoodItem updateFoodItem(Long foodItemId,FoodItem foodItemReq) throws RecordNotFoundException, OptimisticEntityLockException, DataValidationErrorException, ConcurrentModificationException {


//        FoodItem checkIfFoodItemExsists= this.getFoodItemById(foodItemId);

        foodItemValidator.validateUpdateFoodItems(foodItemReq,this);

        try{
            FoodItem foodItem  = this.getFoodItemById(foodItemId);
            foodItem.setPrice(foodItemReq.getPrice());
            foodItem.setName(foodItemReq.getName());
            foodItem.setCategories(foodItemReq.getCategories());
            foodItem.setDescription(foodItemReq.getDescription());
            foodItem.setQuantityPerPlate(foodItemReq.getQuantityPerPlate());
            return foodItem;
        }catch (OptimisticEntityLockException e) {
            throw new ConcurrentModificationException("Failed to update food item due to concurrent modification. Please try again.");
        }
    }
}