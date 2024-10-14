package com.argus.cms.services;

import com.argus.cms.exceptions.EntityNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUserName(username).orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found."));;
        List<GrantedAuthority> authorities = user.getRoles().stream().map(authority -> new
                SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
