package com.argus.cms.userManagement.roles.mappers;

import com.argus.cms.userManagement.roles.dto.RoleDTO;
import com.argus.cms.userManagement.roles.entities.Roles;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    public abstract RoleDTO roleToRoleDTO(Roles role);

    public abstract Roles roleDTOToRole(RoleDTO roleDTO);

    public abstract List<RoleDTO> getRoleDTOListFromRoleList(List<Roles> roles);
}
