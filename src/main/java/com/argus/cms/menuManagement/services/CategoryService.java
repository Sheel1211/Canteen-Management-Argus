package com.argus.cms.menuManagement.services;

import com.argus.cms.menuManagement.entities.Category;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category getCategoryById(Long categoryId);
    public List<Category> findAllCategoriesByCanteenId(Long canteenId);
    public void deleteCategoryById(Long categoryId);
}
