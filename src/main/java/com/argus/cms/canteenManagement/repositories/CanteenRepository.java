package com.argus.cms.canteenManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.userManagement.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanteenRepository extends JpaRepository<Canteen, Long> {

    Canteen findByCanteenUsers(Users user);
}

