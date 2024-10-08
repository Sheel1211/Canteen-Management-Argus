package com.argus.cms.userManagement.roles.controllers;

import com.argus.cms.userManagement.roles.dto.RoleDTO;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.mappers.RoleMapper;
import com.argus.cms.userManagement.roles.services.RoleService;
import com.argus.cms.userManagement.roles.transformers.RoleTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {
    private RoleService roleService;
    private RoleTransformer roleTransformer;
    private RoleMapper roleMapper;

    @PostMapping
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDTO) {

        Roles role = roleTransformer.roleDTOToRole(roleDTO);
        Roles addedRole = roleService.addRole(role);

        RoleDTO createdRoleDTO = roleTransformer.roleToRoleDTO(addedRole);

        return new ResponseEntity<>(createdRoleDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleByID(@PathVariable Long roleId) {
        Roles fetchedRole = roleService.findById(roleId).orElse(null);

        RoleDTO roleDTO = roleTransformer.roleToRoleDTO(fetchedRole);

        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<Roles> roles = roleService.getRoles();

        List<RoleDTO> rolesDTO = roleMapper.getRoleDTOListFromRoleList(roles);

        return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
    }
}
