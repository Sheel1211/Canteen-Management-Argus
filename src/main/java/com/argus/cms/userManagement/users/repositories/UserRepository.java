package com.argus.cms.userManagement.users.repositories;

import com.argus.cms.userManagement.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUserName(String username);
}
