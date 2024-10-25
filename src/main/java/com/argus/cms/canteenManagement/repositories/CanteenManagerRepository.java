package com.argus.cms.canteenManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.entities.CanteenManager;
import com.argus.cms.userManagement.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CanteenManagerRepository extends JpaRepository<CanteenManager, Long> {
    Optional<CanteenManager> findByManagerAndIsActiveTrue(Users manager);
    Optional<CanteenManager> findByManagerAndCanteen(Users manager, Canteen canteen);
}
