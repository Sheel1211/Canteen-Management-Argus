package com.argus.cms.userManagement.users.controllers;

import com.argus.cms.constants.JWTConstants;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.security.CustomUserDetails;
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
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Validated
public class UserController {
    private UserTransformer userTransformer;
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> Login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) throws RecordNotFoundException {
        Map<String,Object> responseMap = userTransformer.loginUser(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
//        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        ResponseCookie jwtCookie = ResponseCookie.from("token", (String) responseMap.get("token"))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(JWTConstants.JWT_EXPIRATION_TIME)
                .sameSite("strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        return new ResponseEntity<>((UserResponseDTO) responseMap.get("user"),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@RequestBody RegistrationRequestDTO registrationRequestDTO) throws RecordNotFoundException, DataValidationErrorException {
//        System.out.println(registrationRequestDTO);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')  or #userId == principal.userId")
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
    public ResponseEntity<UserResponseDTO> getCurrentUser() throws RecordNotFoundException {
        UserResponseDTO userResponseDTO = userTransformer.getCurrentUser();
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
}