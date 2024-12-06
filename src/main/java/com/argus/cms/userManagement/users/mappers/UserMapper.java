package com.argus.cms.userManagement.users.mappers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.services.RoleService;
import com.argus.cms.userManagement.users.dto.RegistrationRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationResponseDTO;
import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import com.argus.cms.userManagement.users.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleService roleService;

    @Mapping(target = "role", source = "role", qualifiedByName = "mapRoleToRoleName")
    public abstract RegistrationResponseDTO userToRegistrationResponseDTO(Users user);

    @Mapping(target = "role", source = "role", qualifiedByName = "mapRoleNameToRole")
    public abstract Users registrationRequestDTOToUser(RegistrationRequestDTO userDTO);

    @Named("mapRoleToRoleName")
    public String mapRoleToRoleName(Roles role) {
        return role.getName();
    }

    @Named("mapRoleNameToRole")
    public Roles mapRoleNameToRole(String roleName) throws RecordNotFoundException {
        return roleService.findByName(roleName);
    }

    public abstract UserResponseDTO userToUserResponseDTO(Users user);
}
