package com.argus.cms.userManagement.users.services;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.security.CustomUserDetails;
import com.argus.cms.security.services.JwtService;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.mappers.UserMapper;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import com.argus.cms.userManagement.users.validation.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserMapper userMapper;
    private UserValidator userValidator;

    @Override
    @Transactional
    public void deleteUserById(Long userId) throws RecordNotFoundException {
        Users user = this.findUserById(userId);
        if(user.getIsDeleted()){
            throw new RecordNotFoundException("User doesn't exist with id " + userId);
        }
        user.setIsDeleted(true);
    }

    @Override
    @Transactional
    public Users saveUser(Users user) throws RecordNotFoundException, DataValidationErrorException {
        userValidator.validateCreateUsers(user,this);
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
    public Map<String,Object> loginUser(String userName, String password) throws RecordNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        Users user = this.findByUserName(userName);
        System.out.println(authentication.getPrincipal());

        if (authentication.isAuthenticated()) {
            Roles userRole = user.getRole();
            String userRoleString = userMapper.mapRoleToRoleName(userRole);
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("token",jwtService.GenerateToken(userName, userRoleString));
            objectMap.put("user",user);
            return objectMap;
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @Override
    public Users getCurrentUser() throws RecordNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return userRepository.findById(customUserDetails.getUserId()).orElseThrow(()->new RecordNotFoundException("User not found."));
        }
        throw new IllegalStateException("No Logged in User Found");
    }
}