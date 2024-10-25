package com.argus.cms.menuManagement.controllers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.FoodItemDTO;
import com.argus.cms.menuManagement.dtos.FoodItemResponseDTO;
import com.argus.cms.menuManagement.dtos.GetAllFoodItemResponseDTO;
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
    public ResponseEntity<FoodItemResponseDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
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
    public ResponseEntity<FoodItemResponseDTO> updateFoodItem(@PathVariable Long foodItemId, @RequestBody FoodItemDTO foodItemReqDTO) throws RecordNotFoundException{
        FoodItemResponseDTO foodItem = foodItemTransformer.updateFoodItem(foodItemId,foodItemReqDTO);
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @DeleteMapping("/{foodItemId}")
    @PreAuthorize("hasRole('ROLE_CANTEEN_MANAGER')")
    public ResponseEntity<String> deleteFoodItemById(@PathVariable Long foodItemId) throws RecordNotFoundException {
        foodItemTransformer.deleteFoodItemById(foodItemId);
        return new ResponseEntity<>("FoodItem with id: ", HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GetAllFoodItemResponseDTO>> getAllFoodItems() {
        List<GetAllFoodItemResponseDTO> foodItems = foodItemTransformer.getAllFoodItems();
        return new ResponseEntity<>(foodItems, HttpStatus.OK);
    }
}