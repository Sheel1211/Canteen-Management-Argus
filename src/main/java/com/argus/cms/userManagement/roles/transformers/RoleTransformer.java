package com.argus.cms.userManagement.roles.transformers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.roles.dto.RoleDTO;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.mappers.RoleMapper;
import com.argus.cms.userManagement.roles.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoleTransformer {
    private RoleMapper roleMapper;
    private RoleService roleService;

    public RoleDTO addRole(RoleDTO roleDTO){
        Roles role = roleMapper.roleDTOToRole(roleDTO);
        Roles addedRole = roleService.addRole(role);
        return roleMapper.roleToRoleDTO(addedRole);
    }

    public RoleDTO getRoleById(Long roleId) throws RecordNotFoundException {
        Roles role = roleService.findById(roleId);
        return roleMapper.roleToRoleDTO(role);
    }

    public List<RoleDTO> getRoles(){
        List<Roles> roles = roleService.getAllRoles();
        return roleMapper.getRoleDTOListFromRoleList(roles);
    }

//    public void deleteRoleById(Long roleId) throws RecordNotFoundException {
//        roleService.deleteById(roleId);
//    }
}
