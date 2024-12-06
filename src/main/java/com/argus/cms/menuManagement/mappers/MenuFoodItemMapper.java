package com.argus.cms.menuManagement.mappers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.MenuFoodItemRequestDTO;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.entities.MenuFoodItem;
import com.argus.cms.menuManagement.services.FoodItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MenuFoodItemMapper {

    @Autowired
    private FoodItemService foodItemService;

    @Mapping(source = "foodItemId", target = "foodItem",qualifiedByName = "mapFoodItemIdToFoodItem")
    public abstract MenuFoodItem toEntity(MenuFoodItemRequestDTO menuFoodItemRequestDTO);

    public abstract MenuFoodItemRequestDTO toDTO(MenuFoodItem menuFoodItem);

    @Named("mapFoodItemIdToFoodItem")
    FoodItem mapFoodItemIdToFoodItem(Long foodItemId) throws RecordNotFoundException {
        return foodItemService.getFoodItemById(foodItemId);
    }
}
