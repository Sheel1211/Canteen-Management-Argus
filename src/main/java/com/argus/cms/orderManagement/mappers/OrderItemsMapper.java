package com.argus.cms.orderManagement.mappers;

import com.argus.cms.batchManagement.entity.Batch;
import com.argus.cms.batchManagement.service.BatchService;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.dtos.OrderFoodCommonDTO;
import com.argus.cms.orderManagement.dtos.OrderItemsDTO;
import com.argus.cms.orderManagement.dtos.OrderItemsResponseDTO;
import com.argus.cms.orderManagement.entities.OrderFoodCommon;
import com.argus.cms.orderManagement.entities.OrderItems;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderItemsMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private OrderFoodCommonMapper orderFoodCommonMapper;


    public abstract OrderItemsResponseDTO toResponseDTO(OrderItems orderItems);


    @Mapping(source = "receiverId", target = "receiver",qualifiedByName = "receiverIdToUser")
    @Mapping(source = "batchId", target = "batch",qualifiedByName = "batchIdToBatch")
    @Mapping(source = "orderedFoodItems", target = "orderedFoodItems",qualifiedByName = "orderedFoodItemListToOrderedFoodCommonList")
    public abstract OrderItems toEntity(OrderItemsDTO orderItemsDTO);


    public abstract List<OrderItems> toEntityList(List<OrderItemsDTO> orderItemsDTOList);

    @Named("receiverIdToUser")
    public Users mapReceiverIdToUser(Long receiverId) throws RecordNotFoundException {
        return userService.findUserById(receiverId);
    }

    @Named("batchIdToBatch")
    public Batch mapBatchIdToBatch(Long batchId) throws RecordNotFoundException {
        return batchService.getBatchById(batchId);
    }

    @Named("orderedFoodItemListToOrderedFoodCommonList")
    public List<OrderFoodCommon> orderedFoodItemListToOrderedFoodCommonList(List<OrderFoodCommonDTO> orderFoodCommonDTO){
        return orderFoodCommonMapper.toEntityList(orderFoodCommonDTO);
    }

    public String batchToString(Batch batch){
        return batch.getTimeSlot().toString();
    }
}