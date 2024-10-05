package com.argus.cms.userManagement.roles.services;

import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    public RoleRepository roleRepository;

    @Override
    public Roles addRole(Roles role) {
            Roles savedRole = roleRepository.save(role);
            return savedRole;
    }

    @Override
    public List<Roles> getRoles() {
        List<Roles> savedRoles = roleRepository.findAll();
        return savedRoles;
    }

    public Optional<Roles> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
