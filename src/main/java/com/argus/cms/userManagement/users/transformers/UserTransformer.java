package com.argus.cms.userManagement.users.transformers;

import com.argus.cms.userManagement.users.dto.RegistrationRequestDTO;
import com.argus.cms.userManagement.users.dto.RegistrationResponseDTO;
import com.argus.cms.userManagement.users.dto.UserResponseDTO;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.mappers.UserMapper;
import com.argus.cms.userManagement.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserTransformer {

    private UserMapper userMapper;
    private UserService userService;

    public String loginUser(String userName, String password){
        return userService.loginUser(userName,password);
    }

    public RegistrationResponseDTO registrationTransformer(RegistrationRequestDTO registrationRequestDTO) {
        Users user = userMapper.registrationRequestDTOToUser(registrationRequestDTO);
        Users createdUser = userService.saveUser(user);
        return userMapper.userToRegistrationResponseDTO(createdUser);
    }

    public RegistrationResponseDTO userToRegistrationResponseDTO(Users user) {
        return userMapper.userToRegistrationResponseDTO(user);
    }

    public UserResponseDTO findUserByIdTransformer(Long userId) {
        Users user = userService.findUserById(userId);
        return userMapper.userToUserResponseDTO(user);
    }

    public void deleteUserByIdTransformer(Long userId) {
        userService.deleteUserById(userId);
    }

    public UserResponseDTO findUserByUserNameTransformer(String userName) {
        Users user = userService.findByUserName(userName);
        return userMapper.userToUserResponseDTO(user);
    }
}
