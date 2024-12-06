package com.argus.cms.menuManagement.repositories;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.menuManagement.entities.Category;
import com.argus.cms.menuManagement.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    List<Menu>findByCanteen(Canteen canteen);
    Optional<Menu> findByDateOfMenuAndCategoryAndCanteen(LocalDate dateOfMenu, Category category, Canteen canteen);
    List<Menu> findByCanteenAndDateOfMenuBetween(Canteen canteen, LocalDate startDate, LocalDate endDate);

    @Query("SELECT m FROM Menu m WHERE m.dateOfMenu >= CURRENT_DATE ORDER BY m.dateOfMenu ASC, m.category.id ASC")
    List<Menu> getUpcomingMenusOfAllCanteens();
}
