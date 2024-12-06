package com.argus.cms.orderManagement.mappers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.services.MenuService;
import com.argus.cms.orderManagement.dtos.OrderDTO;
import com.argus.cms.orderManagement.dtos.OrderItemsDTO;
import com.argus.cms.orderManagement.dtos.OrderItemsResponseDTO;
import com.argus.cms.orderManagement.dtos.OrderResponseDTO;
import com.argus.cms.orderManagement.entities.Order;
import com.argus.cms.orderManagement.entities.OrderItems;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Mapping(source = "ordererId",target = "orderer", qualifiedByName = "userIdToUser")
    @Mapping(source = "menuId",target = "menu", qualifiedByName = "menuIdToMenu")
    @Mapping(source = "orderItems",target = "orderItems",qualifiedByName = "orderItemsDTOToOrderItems")
    public abstract Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "id",target = "orderId")
    public abstract OrderResponseDTO toResponseDTO(Order order);

    @Named("userIdToUser")
    public Users userIdToUser(Long userId) throws RecordNotFoundException {
        return userService.findUserById(userId);
    }

    @Named("menuIdToMenu")
    public Menu menuIdToMenu(Long menuId) throws RecordNotFoundException {
        return menuService.getMenuById(menuId);
    }

    @Named("orderItemsDTOToOrderItems")
    public List<OrderItems> orderItemsDTOToOrderItems(List<OrderItemsDTO> orderItemsDTOS){
        return orderItemsMapper.toEntityList(orderItemsDTOS);
    }

    public OrderItemsResponseDTO mapOrderItemsToResponseDTO(OrderItems orderItems){
        return orderItemsMapper.toResponseDTO(orderItems);
    }

}
