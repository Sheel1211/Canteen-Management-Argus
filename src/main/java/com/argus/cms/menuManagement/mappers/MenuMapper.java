package com.argus.cms.menuManagement.mappers;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.menuManagement.dtos.MenuRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuResponseDTO;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.services.CategoryService;
import com.argus.cms.menuManagement.services.FoodItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MenuMapper {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CanteenService canteenService;

    @Mapping(source = "canteenId",target = "canteen", qualifiedByName = "mapCanteenIdToCanteen")
    @Mapping(source = "foodItemListIds", target = "foodItems")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToCategory")
    public abstract Menu toEntity(MenuRequestDTO menuRequestDTO);

    @Mapping(source = "canteen", target = "canteenName")
    @Mapping(source="category.id", target ="categoryId")
    public abstract MenuResponseDTO toResponseDTO(Menu menu);

    public abstract List<MenuResponseDTO> toResponseDTOs(List<Menu> menus);

    List<FoodItem> map(List<Long> foodItems){
        return foodItems.stream().map((id)->foodItemService.getFoodItemById(id)).collect(Collectors.toList());
    }

    String map(Canteen canteen){
        return canteen.getName();
    }

    @Named("mapCanteenIdToCanteen")
    public Canteen mapCanteenIdToCanteen(Long canteenId)
    {
        return canteenService.getCanteenById(canteenId);
    }

    @Named("mapCategoryIdToCategory")
    public Category mapCategoryIdToCategory(Long categoryId)
    {
        return categoryService.getCategoryById(categoryId);
    }
}
