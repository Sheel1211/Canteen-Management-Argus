package com.argus.cms.userManagement.roles.services;

import com.argus.cms.exceptions.EntityNotFoundException;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    public RoleRepository roleRepository;

    @Override
    @Transactional
    public Roles addRole(Roles role) {
        Roles savedRole = roleRepository.save(role);
        return savedRole;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Roles> getAllRoles() {
        List<Roles> savedRoles = roleRepository.findAll();
        return savedRoles;
    }

    @Override
    @Transactional(readOnly = true)
    public Roles findByName(String roleName) {
        Roles role = roleRepository.findByName(roleName).orElseThrow(() -> new EntityNotFoundException("Role with name " + roleName + " not found!"));
        return role;
    }

    @Override
    @Transactional(readOnly = true)
    public Roles findById(Long roleId) {
        Roles role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role with name " + roleId + " not found!"));
        return role;
    }

    @Override
    @Transactional
    public void deleteById(Long roleId) {
        Roles role = this.findById(roleId);
//        if(role.getIsDeleted()){
//            throw new EntityNotFoundException("Role doesn't exist with id " + roleId);
//        }
//        role.setIsDeleted(true);
    }
}
