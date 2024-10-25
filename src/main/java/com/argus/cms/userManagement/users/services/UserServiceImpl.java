package com.argus.cms.userManagement.users.services;

import com.argus.cms.config.CustomUserDetails;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.services.JwtService;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.mappers.UserMapper;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void deleteUserById(Long userId) throws RecordNotFoundException {
        Users user = this.findUserById(userId);
        if(user.getIsDeleted()){
            throw new EntityNotFoundException("User doesn't exist with id " + userId);
        }
        user.setIsDeleted(true);
    }

    @Override
    @Transactional
    public Users saveUser(Users user) throws RecordNotFoundException{
        Users toSaveUser = userRepository.findByUserName(user.getUserName()).orElse(null);
        if (toSaveUser != null) {
            throw new DataIntegrityViolationException("Username Already Exists! Please choose a different username.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Users findUserById(Long id) throws RecordNotFoundException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User with ID " + id + " not found."));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> getUsers() {
        return userRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Users findByUserName(String username) throws RecordNotFoundException {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RecordNotFoundException("User with username " + username + " not found."));
        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public String loginUser(String userName, String password) throws RecordNotFoundException {
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

    @Override
    public CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        throw new IllegalStateException("No Logged in User Found");
    }
}
