package com.argus.cms.userManagement.roles.controllers;

import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.services.RoleService;
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

    @PostMapping
    public ResponseEntity<Roles> addRole(@RequestBody Roles role) {
        return new ResponseEntity<>(roleService.addRole(role),HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Roles>> getRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }
}
