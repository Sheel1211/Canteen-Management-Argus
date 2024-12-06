package com.argus.cms.userManagement.users.services;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    Users saveUser(Users user) throws RecordNotFoundException, DataValidationErrorException;

    Users findUserById(Long userId) throws RecordNotFoundException;

    void deleteUserById(Long userId) throws RecordNotFoundException;

    List<Users> getUsers();

    Users findByUserName(String username) throws RecordNotFoundException;

    Map<String,Object> loginUser(String userName, String password) throws RecordNotFoundException;

    Users getCurrentUser() throws RecordNotFoundException;
}
