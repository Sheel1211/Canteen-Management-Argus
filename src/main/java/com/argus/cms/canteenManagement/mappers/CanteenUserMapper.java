package com.argus.cms.canteenManagement.mappers;

import com.argus.cms.canteenManagement.dto.CanteenUserDTO;
import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CanteenUserMapper {
    @Autowired
    private CanteenService canteenService;
    @Autowired
    private UserService userService;

    @Mapping(source = "user.id",target = "canteenUserId")
    @Mapping(source = "canteen.id",target = "canteenId")
    public abstract CanteenUserDTO toDTO(CanteenUser canteenUser);

//    @Mapping(target = "canteen.canteenManagers", ignore = true)
    @Mapping(source = "canteenUserId", target ="user", qualifiedByName = "getUserFromUserId")
    @Mapping(source = "canteenId", target ="canteen", qualifiedByName = "getCanteenFromCanteenId")
    public abstract CanteenUser toEntity(CanteenUserDTO canteenUserDTO);

    @Named("getCanteenFromCanteenId")
    Canteen map(Long canteenId) throws RecordNotFoundException {
        return canteenService.getCanteenById(canteenId);
    }

    @Named("getUserFromUserId")
    Users getUserFromUserId(Long userId) throws RecordNotFoundException {
        return userService.findUserById(userId);
    }
}