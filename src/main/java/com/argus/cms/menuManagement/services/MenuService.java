package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Menu;

import java.util.List;

public interface MenuService {
    Menu addMenu(Menu menu) throws RecordNotFoundException, DataValidationErrorException;
    Menu getMenuById(Long menuId) throws RecordNotFoundException;
    List<Menu> getAllMenus();
    void deleteMenuById(Long menuId) throws RecordNotFoundException;
}
