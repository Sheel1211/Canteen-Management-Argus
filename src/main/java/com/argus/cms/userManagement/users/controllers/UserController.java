package com.argus.cms.userManagement.users.controllers;

import com.argus.cms.userManagement.users.dto.LoginRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationResponseDTO;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
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
    private UserService userService;
    private UserTransformer userTransformer;

    @PostMapping("/login")
    public ResponseEntity<String> Login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        String token = userService.loginUser(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity<>("User logged In Successfully",HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO registrationResponseDTO = userTransformer.registrationTransformer(registrationRequestDTO);
        return new ResponseEntity<>(registrationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {

        Users user=userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/userName")
    public ResponseEntity<Users> getUserByUserName(@RequestParam String userName) {

        Users user=userService.findByUserName(userName);
        return ResponseEntity.ok(user);
    }


}