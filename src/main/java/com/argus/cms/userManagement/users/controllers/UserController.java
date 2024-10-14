package com.argus.cms.userManagement.users.controllers;

import com.argus.cms.userManagement.users.dto.LoginRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationResponseDTO;
import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import com.argus.cms.userManagement.users.transformers.UserTransformer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Validated
public class UserController {
    private UserTransformer userTransformer;

    @PostMapping("/login")
    public ResponseEntity<String> Login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        String token = userTransformer.loginUser(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity<>("User logged In Successfully",HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO registrationResponseDTO = userTransformer.registrationTransformer(registrationRequestDTO);
        return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userTransformer.findUserByIdTransformer(id);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        userTransformer.deleteUserByIdTransformer(id);
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/userName")
    public ResponseEntity<UserResponseDTO> getUserByUserName(@RequestParam String userName) {
        UserResponseDTO userResponseDTO = userTransformer.findUserByUserNameTransformer(userName);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

}