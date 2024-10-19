package com.argus.cms.menuManagement.controllers;

import com.argus.cms.menuManagement.dtos.FoodItemDTO;
import com.argus.cms.menuManagement.dtos.FoodItemResponseDTO;
import com.argus.cms.menuManagement.dtos.GetAllFoodItemResponseDTO;
import com.argus.cms.menuManagement.transformers.FoodItemTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/food-items")
public class FoodItemController {
    private final FoodItemTransformer foodItemTransformer;

    @PostMapping
    public ResponseEntity<FoodItemResponseDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemResponseDTO savedFoodItem = foodItemTransformer.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodItemResponseDTO> getFoodItemById(@PathVariable Long foodItemId) {
        FoodItemResponseDTO foodItem = foodItemTransformer.getFoodItemById(foodItemId);
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<String> deleteFoodItemById(@PathVariable Long foodItemId) {
        foodItemTransformer.deleteFoodItemById(foodItemId);
        return new ResponseEntity<>("FoodItem with id: ", HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GetAllFoodItemResponseDTO>> getAllFoodItems() {
        List<GetAllFoodItemResponseDTO> foodItems = foodItemTransformer.getAllFoodItems();
        return new ResponseEntity<>(foodItems, HttpStatus.OK);
    }

    //get foddItems b canteenId

    //get all foodItems available

    //delete food item by a spicific canteen

    //update food item by a spicific canteen


//
//    @GetMapping
//    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
//        List<FoodItemDTO> foodItems = foodItemTransformer.getAllFoodItems();
//        return new ResponseEntity<>(foodItems, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) {
//        foodItemTransformer.deleteFoodItem(id);
//        return new ResponseEntity<>("Food item deleted successfully!", HttpStatus.OK);
//    }
}