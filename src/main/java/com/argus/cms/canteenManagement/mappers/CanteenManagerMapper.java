package com.argus.cms.canteenManagement.mappers;

import com.argus.cms.canteenManagement.dto.CanteenManagerDTO;
import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.entities.CanteenManager;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CanteenManagerMapper {
    @Autowired
    private CanteenService canteenService;
    @Autowired
    private UserService userService;

    @Mapping(source = "manager.id",target = "canteenManagerId")
    @Mapping(source = "canteen.id",target = "canteenId")
    public abstract CanteenManagerDTO toDTO(CanteenManager canteenManager);

//    @Mapping(target = "canteen.canteenManagers", ignore = true)
    @Mapping(source = "canteenManagerId", target ="manager", qualifiedByName = "getUserFromUserId")
    @Mapping(source = "canteenId", target ="canteen", qualifiedByName = "getCanteenFromCanteenId")
    public abstract CanteenManager toEntity(CanteenManagerDTO canteenManagerDTO);

    @Named("getCanteenFromCanteenId")
    Canteen map(Long canteenId) throws RecordNotFoundException {
        return canteenService.getCanteenById(canteenId);
    }

    @Named("getUserFromUserId")
    Users getUserFromUserId(Long userId) throws RecordNotFoundException {
        return userService.findUserById(userId);
    }
}