package com.argus.cms.userManagement.users.transformers;

import com.argus.cms.userManagement.users.dto.UserDTO;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserTransformer {
    private UserMapper userMapper;

    public Users userDTOToUsers(UserDTO userDTO){
        return userMapper.userDTOToUser(userDTO);
    }

    public UserDTO userToUsersDTO(Users user){
        return userMapper.userToUserDTO(user);
    }
}
