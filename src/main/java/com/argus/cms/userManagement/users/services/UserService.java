package com.argus.cms.userManagement.users.services;

import com.argus.cms.userManagement.users.entities.Users;

import java.util.List;

public interface UserService {
    Users saveUser(Users user);
    Users getUser(Long userId);
    List<Users> getUsers();
}
