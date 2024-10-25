package com.argus.cms.menuManagement.controllers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.CategoryDTO;
import com.argus.cms.menuManagement.dtos.CategoryResponseDTO;
import com.argus.cms.menuManagement.services.CategoryService;
import com.argus.cms.menuManagement.transformers.CategoryTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryTransformer categoryTransformer;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryResponseDTO savedCategory = categoryTransformer.addCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) throws RecordNotFoundException {
        CategoryResponseDTO categoryResponseDTO = categoryTransformer.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategoryById(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) throws RecordNotFoundException {
        CategoryResponseDTO categoryResponseDTO = categoryTransformer.updateCategoryById(categoryId,categoryDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/canteen/{canteenId}")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategoriesByCanteenId(@PathVariable Long canteenId) throws RecordNotFoundException
    {
        List<CategoryResponseDTO>  categoryResponseDTOs = categoryTransformer.getAllCategoriesByCanteenId(canteenId);
        return new ResponseEntity<>(categoryResponseDTOs,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) throws RecordNotFoundException
    {
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
