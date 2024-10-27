package com.argus.cms.menuManagement.transformers;

import com.argus.cms.exceptions.ConcurrentModificationException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.FoodItemDTO;
import com.argus.cms.menuManagement.dtos.FoodItemResponseDTO;
import com.argus.cms.menuManagement.dtos.GetAllFoodItemResponseDTO;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.mappers.FoodItemMapper;
import com.argus.cms.menuManagement.services.FoodItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FoodItemTransformer {

    private final FoodItemMapper foodItemMapper;
    private final FoodItemService foodItemService;

    public FoodItemResponseDTO addFoodItem(FoodItemDTO foodItemDTO) throws RecordNotFoundException, DataValidationErrorException {
        FoodItem foodItem = foodItemMapper.toEntity(foodItemDTO);
        FoodItem addedFoodItem = foodItemService.addFoodItem(foodItem);
        return foodItemMapper.toResponseDTO(addedFoodItem);
    }

    public  FoodItemResponseDTO getFoodItemById(Long foodItemId) throws RecordNotFoundException {
        FoodItem foodItem = foodItemService.getFoodItemById(foodItemId);
        return foodItemMapper.toResponseDTO(foodItem);
    }

    public  FoodItemResponseDTO updateFoodItem(Long foodItemId,FoodItemDTO foodItemDTO) throws RecordNotFoundException, DataValidationErrorException, ConcurrentModificationException {
        FoodItem reqFoodItem= foodItemMapper.toEntity(foodItemDTO);
        FoodItem updatedFoodItem = foodItemService.updateFoodItem(foodItemId,reqFoodItem);
        return foodItemMapper.toResponseDTO(updatedFoodItem);
    }

    public void deleteFoodItemById(Long foodItemId) throws RecordNotFoundException {
        foodItemService.deleteFoodItemById(foodItemId);
    }

    public List<GetAllFoodItemResponseDTO> getAllFoodItems()
    {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        List<GetAllFoodItemResponseDTO> foodItemResponseDTOS = foodItemMapper.toGetAllResponseDTO(foodItems);
        return foodItemResponseDTOS;
    }

}
