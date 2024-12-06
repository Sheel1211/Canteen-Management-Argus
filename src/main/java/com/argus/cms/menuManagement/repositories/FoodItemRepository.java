package com.argus.cms.menuManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {

    Optional<FoodItem> findByNameAndCanteen(String name, Canteen canteen);
    List<FoodItem> findByCanteen(Canteen canteen);
    List<FoodItem> findByCanteenAndCategories(Canteen canteen, Category category);
}
