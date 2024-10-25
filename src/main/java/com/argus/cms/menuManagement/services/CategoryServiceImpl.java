package com.argus.cms.menuManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.config.CustomUserDetails;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.repositories.CategoryRepository;
import com.argus.cms.userManagement.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final CanteenService canteenService;

    @Override
    @Transactional
    public Category addCategory(Category category) {
        CustomUserDetails userDetails = userService.getCurrentUser();
        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long categoryId) throws RecordNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new RecordNotFoundException("Category not found with id " + categoryId));
        return category;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategoriesByCanteenId(Long canteenId) throws RecordNotFoundException {
        Canteen canteen = canteenService.getCanteenById(canteenId);
        List<Category> categories = categoryRepository.findByCanteen(canteen);
        return categories;
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long categoryId) throws RecordNotFoundException
    {
        Category category = this.getCategoryById(categoryId);
        if(category.getIsDeleted()){
            throw new RecordNotFoundException("Category doesn't exist with id " + categoryId);
        }
        category.setIsDeleted(true);
    }

    @Override
    @Transactional
    public Category updateCategoryById(Long categoryId, Category category) throws RecordNotFoundException {
        Category fetchedCategory = this.getCategoryById(categoryId);
        fetchedCategory.setType(category.getType());
        return fetchedCategory;
    }
}
