package com.argus.cms.menuManagement.mappers;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.FoodItemDTO;
import com.argus.cms.menuManagement.dtos.FoodItemResponseDTO;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.services.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FoodItemMapper {

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private CategoryService categoryService;

    public abstract FoodItemResponseDTO toResponseDTO(FoodItem foodItem);

    @Mapping(target = "canteen", source = "canteenId", qualifiedByName = "mapCanteenIdToCanteen")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "mapCategoryIdsToCategories")
    public abstract FoodItem toEntity(FoodItemDTO foodItemDTO);

    public abstract List<FoodItemResponseDTO> toResponseDTOList(List<FoodItem> foodItems);

//    @Mapping(target= "canteen" , source= "canteen")
//    @Mapping(target= "categories" , source= "categories")
//    public abstract List<GetAllFoodItemResponseDTO> toGetAllResponseDTO(List<FoodItem> foodItems);

    @Named("mapCanteenIdToCanteen")
    public Canteen mapCanteen(Long canteenId) throws RecordNotFoundException {
        if (canteenId == null) {
            return null;
        }
        return canteenService.getCanteenById(canteenId);
    }

    @Named("mapCategoryIdsToCategories")
    public List<Category> mapCategoryIdsToCategories (List<Long> categoryIds) throws RecordNotFoundException {
        if (categoryIds.isEmpty()) {
            return new ArrayList<>();
        }
        List<Category> categories = new ArrayList<>();
//        categoryIds.forEach((categoryId) -> {
//            categories.add(categoryService.getCategoryById(categoryId));
//        });
        for (Long categoryId : categoryIds) {
            categories.add(categoryService.getCategoryById(categoryId));
        }
        return categories;
    }

    protected String map(Canteen canteen) {
        return canteen != null ? canteen.getName() : null;
    }

    protected List<String> map(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        return categories.stream()
                .map(Category::getType)
                .collect(Collectors.toList());
    }
}