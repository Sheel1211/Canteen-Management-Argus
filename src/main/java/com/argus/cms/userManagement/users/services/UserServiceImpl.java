package com.argus.cms.userManagement.users.services;

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
import java.util.Optional;
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

    @Override
    public Users getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public String loginUser(String userName, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        Users user = this.findByUserName(userName).orElse(null);

        if (authentication.isAuthenticated()) {
            Set<Roles> userRoles = user.getRoles();
            Set<String> userRolesString = userMapper.mapRolesToRoleNames(userRoles);
            return jwtService.GenerateToken(userName, userRolesString);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }


}
