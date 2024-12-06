package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId) throws RecordNotFoundException;

    List<Category> findAllCategoriesByCanteenId(Long canteenId) throws RecordNotFoundException;

    void deleteCategoryById(Long categoryId) throws RecordNotFoundException;

    Category updateCategoryById(Long categoryId, Category category) throws RecordNotFoundException;
}
