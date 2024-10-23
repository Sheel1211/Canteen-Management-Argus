package com.argus.cms.menuManagement.transformers;

import com.argus.cms.menuManagement.dtos.CategoryDTO;
import com.argus.cms.menuManagement.dtos.CategoryResponseDTO;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.mappers.CategoryMapper;
import com.argus.cms.menuManagement.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CategoryTransformer {
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
//        System.out.println(category.getCanteen().getCreatedBy());
        return categoryMapper.toResponseDTO(category);
    }

    public CategoryResponseDTO addCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryService.addCategory(category);
        CategoryResponseDTO savedCategoryResponseDTO = categoryMapper.toResponseDTO(savedCategory);
        return savedCategoryResponseDTO;
    }

    public List<CategoryResponseDTO> getAllCategoriesByCanteenId(Long canteenId)
    {
        List<Category> categories = categoryService.findAllCategoriesByCanteenId(canteenId);
        List<CategoryResponseDTO> categoryResponseDTOs= categoryMapper.toResponseListDTO(categories);
        return categoryResponseDTOs;
    }

    public CategoryResponseDTO updateCategoryById(Long categoryId,CategoryDTO categoryDTO) {
        Category categoryReq = categoryMapper.toEntity(categoryDTO);
        Category category = categoryService.updateCategoryById(categoryId,categoryReq);
        return categoryMapper.toResponseDTO(category);
    }


}