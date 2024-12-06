package com.argus.cms.orderManagement.mappers;


import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.FoodItem;
import com.argus.cms.menuManagement.services.FoodItemService;
import com.argus.cms.orderManagement.dtos.OrderFoodCommonDTO;
import com.argus.cms.orderManagement.dtos.OrderFoodCommonResponseDTO;
import com.argus.cms.orderManagement.entities.OrderFoodCommon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderFoodCommonMapper {

    @Autowired
    private FoodItemService foodItemService;


    public abstract OrderFoodCommonResponseDTO toResponseDTO(OrderFoodCommon orderFoodCommon);

    @Mapping(source = "foodItemId", target = "foodItem", qualifiedByName = "foodItemIdToFoodItem")
    public abstract OrderFoodCommon toEntity(OrderFoodCommonDTO orderFoodCommonDTO);

//    public abstract List<OrderFoodCommonDTO> toDTOList(List<OrderFoodCommon> orderFoodCommons);

    public abstract List<OrderFoodCommon> toEntityList(List<OrderFoodCommonDTO> orderFoodCommonDTOs);


    @Named("foodItemIdToFoodItem")
    public FoodItem mapFoodItemIdToFoodItem(Long foodItemId) throws RecordNotFoundException {
        return foodItemService.getFoodItemById(foodItemId);
    }
}