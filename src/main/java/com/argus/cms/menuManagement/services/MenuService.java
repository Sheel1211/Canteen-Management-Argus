package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu addMenu(Menu menu) throws RecordNotFoundException, DataValidationErrorException;
    Menu getMenuById(Long menuId) throws RecordNotFoundException;
    List<Menu> getAllMenus();
    List<Menu> getAllMenuByCanteenId(Long canteenId, LocalDate currentDate) throws RecordNotFoundException;
    void deleteMenuById(Long menuId) throws RecordNotFoundException;
    Menu getMenuByDateAndCategory(Menu menu) throws RecordNotFoundException;
    List<Menu> getUpcomingMenusOfAllCanteens();
}
