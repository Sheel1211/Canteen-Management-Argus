package com.argus.cms.userManagement.roles.controllers;

import com.argus.cms.userManagement.roles.dto.RoleDTO;
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
    private RoleTransformer roleTransformer;

    @PostMapping
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO savedRoleDTO = roleTransformer.addRole(roleDTO);
        return new ResponseEntity<>(savedRoleDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleByID(@PathVariable Long roleId) {
        RoleDTO roleDTO = roleTransformer.getRoleById(roleId);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> rolesDTO = roleTransformer.getRoles();
        return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteRoleById(@PathVariable Long roleId){
        roleTransformer.deleteRoleById(roleId);
        return new ResponseEntity<>("Role Deleted Successfully !",HttpStatus.OK);
    }
}
