package com.argus.cms.menuManagement.controllers;

import com.argus.cms.exceptions.ConcurrentModificationException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.FoodItemDTO;
import com.argus.cms.menuManagement.dtos.FoodItemRequestDTO;
import com.argus.cms.menuManagement.dtos.FoodItemResponseDTO;
import com.argus.cms.menuManagement.transformers.FoodItemTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/food-items")
public class FoodItemController {
    private final FoodItemTransformer foodItemTransformer;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CANTEEN_MANAGER')")
    public ResponseEntity<FoodItemResponseDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) throws RecordNotFoundException, DataValidationErrorException {
        FoodItemResponseDTO savedFoodItem = foodItemTransformer.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodItemResponseDTO> getFoodItemById(@PathVariable Long foodItemId) throws RecordNotFoundException {
        FoodItemResponseDTO foodItem = foodItemTransformer.getFoodItemById(foodItemId);
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @PutMapping("/{foodItemId}")
    @PreAuthorize("hasRole('ROLE_CANTEEN_MANAGER')")
    public ResponseEntity<FoodItemResponseDTO> updateFoodItem(@PathVariable Long foodItemId, @RequestBody FoodItemDTO foodItemReqDTO) throws RecordNotFoundException, DataValidationErrorException, ConcurrentModificationException {
        FoodItemResponseDTO foodItem = foodItemTransformer.updateFoodItem(foodItemId,foodItemReqDTO);
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @DeleteMapping("/{foodItemId}")
    @PreAuthorize("hasRole('ROLE_CANTEEN_MANAGER')")
    public ResponseEntity<String> deleteFoodItemById(@PathVariable Long foodItemId) throws RecordNotFoundException {
        foodItemTransformer.deleteFoodItemById(foodItemId);
        return new ResponseEntity<>("FoodItem with id: ", HttpStatus.OK);
    }

    @GetMapping("/canteen/{canteenId}")
    public ResponseEntity<List<FoodItemResponseDTO>> getFoodItemsByCanteenId(@PathVariable Long canteenId) throws RecordNotFoundException {
        List<FoodItemResponseDTO> foodItems = foodItemTransformer.getFoodItemsByCanteenId(canteenId);
        return new ResponseEntity<>(foodItems,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemResponseDTO>> getAllFoodItems() {
        List<FoodItemResponseDTO> foodItems = foodItemTransformer.getAllFoodItems();
        return new ResponseEntity<>(foodItems, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<List<FoodItemResponseDTO>> getFoodItemsByCanteenIdAndCategory(@RequestBody FoodItemRequestDTO foodItemRequestDTO) throws RecordNotFoundException {
        List<FoodItemResponseDTO> foodItemResponseDTOS = foodItemTransformer.getFoodItemsByCanteenIdAndCategory(foodItemRequestDTO.getCanteenId(),foodItemRequestDTO.getCategoryId());
        return new ResponseEntity<>(foodItemResponseDTOS, HttpStatus.OK);
    }
}