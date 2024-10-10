package com.argus.cms.userManagement.users.mappers;

import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.services.RoleService;
import com.argus.cms.userManagement.users.dto.RegistrationRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationResponseDTO;
import com.argus.cms.userManagement.users.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleService roleService;

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToRoleNames")
    public abstract RegistrationResponseDTO userToRegistrationResponseDTO(Users user);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleNamesToRoles")
    public abstract Users registrationRequestDTOToUser(RegistrationRequestDTO userDTO);

    @Named("mapRolesToRoleNames")
    public Set<String> mapRolesToRoleNames(Set<Roles> roles) {
        return roles.stream().map(Roles::getName).collect(Collectors.toSet());
    }

    @Named("mapRoleNamesToRoles")
    public Set<Roles> mapRoleNamesToRoles(Set<String> roleNames) {
        Set<Roles> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Optional<Roles> role = roleService.findByName(roleName);
            role.ifPresent(roles::add);
        }
        return roles;
    }
}
