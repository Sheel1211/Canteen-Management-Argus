package com.argus.cms.userManagement.users.services;

import com.argus.cms.userManagement.users.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Users saveUser(Users user);

    Users findUserById(Long userId);

    List<Users> getUsers();

    Users findByUserName(String username);

    String loginUser(String userName, String password);
}
