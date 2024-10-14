package com.argus.cms.canteenManagement.repositories;

import com.argus.cms.canteenManagement.entities.CanteenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanteenUserRepository extends JpaRepository<CanteenUser, Long> {

}
