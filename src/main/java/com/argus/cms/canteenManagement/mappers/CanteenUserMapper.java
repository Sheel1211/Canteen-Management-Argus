package com.argus.cms.canteenManagement.mappers;

import com.argus.cms.canteenManagement.dto.CanteenUserDTO;
import com.argus.cms.canteenManagement.entities.CanteenUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CanteenUserMapper {

    @Mapping(source = "canteenUserId.id",target = "canteenUserId")
    CanteenUserDTO canteenUserToCanteenUserDTO(CanteenUser canteenUser);

    @Mapping(source = "canteenUserId",target = "canteenUserId.id")
    CanteenUser canteenUserDTOToCanteenUser(CanteenUserDTO canteenUserDTO);
}