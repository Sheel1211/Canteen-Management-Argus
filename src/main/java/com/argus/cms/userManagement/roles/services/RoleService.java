package com.argus.cms.userManagement.roles.services;

import com.argus.cms.userManagement.roles.entities.Roles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public Roles addRole(Roles role);
    public List<Roles> getAllRoles();
    public Roles findByName(String name);
    public Roles findById(Long id);
    public void deleteById(Long roleId);
}
