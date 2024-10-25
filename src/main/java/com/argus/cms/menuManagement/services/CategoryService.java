package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Category;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category getCategoryById (Long categoryId) throws RecordNotFoundException;
    public List<Category> findAllCategoriesByCanteenId(Long canteenId) throws RecordNotFoundException;
    public void deleteCategoryById(Long categoryId) throws RecordNotFoundException;
    public Category updateCategoryById(Long categoryId,Category category) throws RecordNotFoundException;
}
