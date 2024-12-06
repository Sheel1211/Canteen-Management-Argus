package com.argus.cms.menuManagement.mappers;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.MenuFetchRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuFoodItemRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuResponseDTO;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.entities.MenuFoodItem;
import com.argus.cms.menuManagement.services.CategoryService;
import com.argus.cms.menuManagement.services.FoodItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MenuMapper {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CanteenService canteenService;
    
    @Autowired
    private MenuFoodItemMapper menuFoodItemMapper;

    @Mapping(source = "canteenId",target = "canteen", qualifiedByName = "mapCanteenIdToCanteen")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToCategory")
    @Mapping(source = "menuFoodItemList", target = "menuFoodItems",qualifiedByName = "mapMenuFoodItemDTOToEntity")
    public abstract Menu toEntity(MenuRequestDTO menuRequestDTO);

    @Mapping(source = "canteenId",target = "canteen", qualifiedByName = "mapCanteenIdToCanteen")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToCategory")
    public abstract Menu toEntityFromMenuFetchDTO(MenuFetchRequestDTO menuFetchRequestDTO);

    @Mapping(source = "canteen.name", target = "canteenName")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "menuFoodItems", target = "menuFoodItemList")
    public abstract MenuResponseDTO toResponseDTO(Menu menu);

    public abstract List<MenuResponseDTO> toResponseDTOs(List<Menu> menus);

    List<FoodItem> map(List<Long> foodItems) throws RecordNotFoundException{
        List<FoodItem> foodItemList = new ArrayList<>();
        for (Long id : foodItems) {
            foodItemList.add(foodItemService.getFoodItemById(id));
        }
//        return foodItems.stream().map((id)->foodItemService.getFoodItemById(id)).collect(Collectors.toList());
        return foodItemList;
    }


//    String map(Canteen canteen){
//        return canteen.getName();
//    }

    @Named("mapCanteenIdToCanteen")
    public Canteen mapCanteenIdToCanteen(Long canteenId) throws RecordNotFoundException {
        return canteenService.getCanteenById(canteenId);
    }

    @Named("mapCategoryIdToCategory")
    public Category mapCategoryIdToCategory(Long categoryId) throws RecordNotFoundException
    {
        return categoryService.getCategoryById(categoryId);
    }
    
    @Named("mapMenuFoodItemDTOToEntity")
    public MenuFoodItem mapMenuFoodItemDTOToEntity(MenuFoodItemRequestDTO menuFoodItemRequestDTO){
        return menuFoodItemMapper.toEntity(menuFoodItemRequestDTO);
    }
}