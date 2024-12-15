package com.argus.cms.userManagement.users.repositories;

import com.argus.cms.userManagement.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUserName(String username);

    @Query("SELECT u FROM Users u WHERE u.isDeleted = false AND u.userName LIKE CONCAT('%', :pattern, '%')")
    List<Users> findUsersByRegex(@Param("pattern") String pattern);
}
