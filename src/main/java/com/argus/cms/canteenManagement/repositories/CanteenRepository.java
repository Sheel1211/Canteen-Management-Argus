package com.argus.cms.canteenManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CanteenRepository extends JpaRepository<Canteen, Long> {

    @Override
    Optional<Canteen> findById(Long aLong);
}

