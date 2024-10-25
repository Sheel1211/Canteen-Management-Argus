package com.argus.cms.audit;

import com.argus.cms.userManagement.users.entities.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<Users> auditorProvider() {
        return new AuditorAwareImpl();
    }
}