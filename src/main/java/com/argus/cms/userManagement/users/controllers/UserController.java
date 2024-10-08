package com.argus.cms.userManagement.users.controllers;

import com.argus.cms.dto.JwtResponseDTO;
import com.argus.cms.userManagement.users.dto.LoginRequestDTO;
import com.argus.cms.userManagement.users.dto.UserDTO;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import com.argus.cms.userManagement.users.transformers.UserTransformer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserTransformer userTransformer;

    @PostMapping("/login")
    public JwtResponseDTO Login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        String token = userService.loginUser(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
        return JwtResponseDTO.builder().accessToken(token).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        Users user = userTransformer.userDTOToUsers(userDTO);
        Users createdUser = userService.saveUser(user);
        UserDTO reponseUserDTO = userTransformer.userToUsersDTO(createdUser);
        return new ResponseEntity<>(reponseUserDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> loginWithExceptionHandler() {
        return ResponseEntity.ok("Success");
    }
}