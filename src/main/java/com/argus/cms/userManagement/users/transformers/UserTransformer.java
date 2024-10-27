package com.argus.cms.userManagement.users.transformers;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
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

    public String loginUser(String userName, String password) throws RecordNotFoundException {
        return userService.loginUser(userName,password);
    }

    public RegistrationResponseDTO registrationTransformer(RegistrationRequestDTO registrationRequestDTO) throws RecordNotFoundException, DataValidationErrorException {
        Users user = userMapper.registrationRequestDTOToUser(registrationRequestDTO);
        Users createdUser = userService.saveUser(user);
        return userMapper.userToRegistrationResponseDTO(createdUser);
    }

    public RegistrationResponseDTO userToRegistrationResponseDTO(Users user) {
        return userMapper.userToRegistrationResponseDTO(user);
    }

    public UserResponseDTO findUserByIdTransformer(Long userId) throws RecordNotFoundException {
        Users user = userService.findUserById(userId);
        return userMapper.userToUserResponseDTO(user);
    }

    public void deleteUserByIdTransformer(Long userId) throws RecordNotFoundException {
        userService.deleteUserById(userId);
    }

    public UserResponseDTO findUserByUserNameTransformer(String userName) throws RecordNotFoundException {
        Users user = userService.findByUserName(userName);
        return userMapper.userToUserResponseDTO(user);
    }
}
