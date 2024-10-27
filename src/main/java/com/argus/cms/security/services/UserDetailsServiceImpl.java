package com.argus.cms.security.services;

import com.argus.cms.security.CustomUserDetails;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        Users user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Record not found with userId"));

        List<GrantedAuthority> authorities = user.getRoles().stream().map(authority -> new
                SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new CustomUserDetails(user.getId(), user.getUserName(), user.getPassword(), authorities);
    }
}