package com.argus.cms.userManagement.users.services;

import com.argus.cms.exceptions.EntityNotFoundException;
import com.argus.cms.services.JwtService;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.mappers.UserMapper;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserMapper userMapper;

    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Users findUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));
        return user;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));
        userRepository.deleteById(userId);
    }


    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public Users findByUserName(String username) {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found."));
        return user;
    }


    @Override
    public String loginUser(String userName, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        Users user = this.findByUserName(userName);

        if (authentication.isAuthenticated()) {
            Set<Roles> userRoles = user.getRoles();
            Set<String> userRolesString = userMapper.mapRolesToRoleNames(userRoles);
            return jwtService.GenerateToken(userName, userRolesString);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
