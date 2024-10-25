package com.argus.cms.menuManagement.services;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.repositories.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    //one canteen
    @Override
    @Transactional(readOnly = true)
    public Menu getMenuById(Long menuId) throws RecordNotFoundException {
        return menuRepository.findById(menuId).orElseThrow(()-> new RecordNotFoundException("Menu not found with the Id : "+ menuId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteMenuById(Long menuId) throws RecordNotFoundException {

        Menu menu = this.getMenuById(menuId);
        if(menu.getIsDeleted()){
            throw new RecordNotFoundException("Menu doesn't exist with id " + menuId);
        }
        menu.setIsDeleted(true);
        menuRepository.save(menu);
    }
}
