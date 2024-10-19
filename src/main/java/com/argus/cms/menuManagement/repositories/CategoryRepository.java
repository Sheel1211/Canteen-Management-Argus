package com.argus.cms.menuManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.menuManagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByCanteen(Canteen canteen);
}
