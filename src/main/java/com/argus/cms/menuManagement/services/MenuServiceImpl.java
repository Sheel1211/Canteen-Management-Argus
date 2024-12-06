package com.argus.cms.menuManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.repositories.MenuRepository;
import com.argus.cms.menuManagement.validation.MenuValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;
    private final MenuValidator menuValidator;
    private final CanteenService canteenService;

    @Override
    @Transactional
    public Menu addMenu(Menu menu) throws RecordNotFoundException, DataValidationErrorException {

//        menuValidator.validateCreateMenu(menu,this);
        System.out.println("-------------------" + menu);

        return menuRepository.save(menu);
    }

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
    public List<Menu> getAllMenuByCanteenId(Long canteenId, LocalDate currentDate) throws RecordNotFoundException {
        Canteen canteen= canteenService.getCanteenById(canteenId);

        YearMonth yearMonth = YearMonth.from(currentDate);


        return menuRepository.findByCanteenAndDateOfMenuBetween(
                canteen,
                yearMonth.atDay(1),
                yearMonth.atEndOfMonth()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Menu getMenuByDateAndCategory(Menu menu) throws RecordNotFoundException {

        System.out.println(menu);
        return menuRepository.findByDateOfMenuAndCategoryAndCanteen(menu.getDateOfMenu(),menu.getCategory(),menu.getCanteen())
                .orElseThrow(()-> new RecordNotFoundException("Menu not found with the Date and Category : "+ menu.getDateOfMenu() + " " + menu.getCategory()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getUpcomingMenusOfAllCanteens() {
        List<Menu> menus = menuRepository.getUpcomingMenusOfAllCanteens();
        return menus;
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
