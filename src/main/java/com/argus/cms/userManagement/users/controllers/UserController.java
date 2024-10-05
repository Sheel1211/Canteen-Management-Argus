package com.argus.cms.userManagement.users.controllers;

import com.argus.cms.dto.AuthRequestDTO;
import com.argus.cms.dto.JwtResponseDTO;
import com.argus.cms.services.JwtService;
import com.argus.cms.userManagement.roles.entities.Roles;
import com.argus.cms.userManagement.roles.services.RoleService;
import com.argus.cms.userManagement.users.dto.UserDTO;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JwtResponseDTO Login(@RequestBody AuthRequestDTO authRequestDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(), authRequestDTO.getPassword()));

            if (authentication.isAuthenticated()) {
                return JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUserName())).build();
            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        Users createdUser;

        try {
            Set<Roles> roles = new HashSet<>();
            for (String roleName : userDTO.getRoles()) {
                Optional<Roles> role = roleService.findByName(roleName);
                role.ifPresent(roles::add);
            }

            Users user = Users.builder()
                    .userName(userDTO.getUserName())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .roles(roles)
                    .balance(0)
                    .isActive(true)
                    .build();

            createdUser = userService.saveUser(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Set<String> userDtoRoles = new HashSet<>();
        userDtoRoles = createdUser.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet());

        return new ResponseEntity<>(UserDTO.builder().userName(createdUser.getUserName()).roles(userDtoRoles).build(), HttpStatus.CREATED);
    }
}