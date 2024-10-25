package com.argus.cms.userManagement.users.controllers;

import com.argus.cms.config.CustomUserDetails;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.dto.LoginRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationResponseDTO;
import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import com.argus.cms.userManagement.users.services.UserService;
import com.argus.cms.userManagement.users.transformers.UserTransformer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Validated
public class UserController {
    private UserTransformer userTransformer;
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> Login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) throws RecordNotFoundException {
        String token = userTransformer.loginUser(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity<>("User logged In Successfully",HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) throws RecordNotFoundException {
        System.out.println(registrationRequestDTO);
        RegistrationResponseDTO registrationResponseDTO = userTransformer.registrationTransformer(registrationRequestDTO);
        return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == principal.userId")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) throws RecordNotFoundException {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(customUserDetails.getUserId());
        UserResponseDTO userResponseDTO = userTransformer.findUserByIdTransformer(userId);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId) throws RecordNotFoundException {
        userTransformer.deleteUserByIdTransformer(userId);
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/userName")
    public ResponseEntity<UserResponseDTO> getUserByUserName(@RequestParam String userName) throws RecordNotFoundException {
        UserResponseDTO userResponseDTO = userTransformer.findUserByUserNameTransformer(userName);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<CustomUserDetails> getCurrentUser(){
        CustomUserDetails customUserDetails = userService.getCurrentUser();
        return new ResponseEntity<>(customUserDetails,HttpStatus.OK);
    }
}