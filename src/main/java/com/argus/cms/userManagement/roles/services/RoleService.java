package com.argus.cms.userManagement.roles.services;

import com.argus.cms.userManagement.roles.entities.Roles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    public Roles addRole(Roles role);
    public List<Roles> getRoles();
    public Optional<Roles> findByName(String name);
}
