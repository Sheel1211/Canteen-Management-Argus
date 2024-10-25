package com.argus.cms.audit;

import com.argus.cms.config.CustomUserDetails;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class AuditorAwareImpl implements AuditorAware<Users> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Users> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is not authenticated or anonymous, return a "SYSTEM" user
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of(createSystemUser());
        }

        // Retrieve user details
        Long userId = getUserIdFromAuthentication(authentication);
        if (userId != null) {
            return userRepository.findById(userId);
        }

        // If user ID is not found, return a "SYSTEM" user
        return Optional.of(createSystemUser());
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        // Assuming your custom user details object contains the userId
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            return customUserDetails.getUserId();
        }
        return null;
    }

    // Create a system user if no authenticated user is available
    private Users createSystemUser() {
        Users systemUser = new Users();
        systemUser.setId(0L);
        systemUser.setUserName("SYSTEM");
        systemUser.setIsActive(false); // Inactive to indicate it's a system user
        return systemUser;
    }
}