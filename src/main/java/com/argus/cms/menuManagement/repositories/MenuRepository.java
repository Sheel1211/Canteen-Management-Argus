package com.argus.cms.menuManagement.repositories;

import com.argus.cms.menuManagement.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
}
