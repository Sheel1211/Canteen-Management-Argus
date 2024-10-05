package com.argus.cms.userManagement.users.services;

import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
