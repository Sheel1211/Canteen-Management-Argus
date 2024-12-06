package com.argus.cms.canteenManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.userManagement.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CanteenUserRepository extends JpaRepository<CanteenUser, Long> {
    Optional<CanteenUser> findByUserAndIsActiveTrue(Users manager);
    Optional<CanteenUser> findByUserAndCanteen(Users manager, Canteen canteen);
    Optional<CanteenUser> findByUserAndCanteenAndIsActiveTrue(Users manager, Canteen canteen);
}
