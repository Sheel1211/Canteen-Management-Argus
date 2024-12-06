package com.argus.cms.userManagement.roles.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.roles.entities.Roles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
     Roles addRole(Roles role);
     List<Roles> getAllRoles();
     Roles findByName(String name) throws RecordNotFoundException;
     Roles findById(Long id) throws RecordNotFoundException;
//     void deleteById(Long roleId) throws RecordNotFoundException;
}
