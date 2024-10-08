package com.argus.cms.userManagement.roles.transformers;

import com.argus.cms.userManagement.roles.dto.RoleDTO;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.mappers.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleTransformer {
    private RoleMapper roleMapper;


    public Roles roleDTOToRole(RoleDTO roleDTO){
        return roleMapper.roleDTOToRole(roleDTO);
    }

    public RoleDTO roleToRoleDTO(Roles role){
        return roleMapper.roleToRoleDTO(role);
    }
}
