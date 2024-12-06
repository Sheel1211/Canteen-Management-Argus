package com.argus.cms.menuManagement.transformers;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.MenuFetchRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuResponseDTO;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.mappers.MenuMapper;
import com.argus.cms.menuManagement.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class MenuTransformer {
    private final MenuMapper menuMapper;
    private final MenuService menuService;

    public MenuResponseDTO addMenu(MenuRequestDTO menuRequestDTO) throws RecordNotFoundException, DataValidationErrorException {
        Menu menu = menuMapper.toEntity(menuRequestDTO);
        Menu addedMenu = menuService.addMenu(menu);
        return menuMapper.toResponseDTO(addedMenu);
    }

    public MenuResponseDTO getMenuByDateAndCategory(MenuFetchRequestDTO menuFetchRequestDTO) throws RecordNotFoundException {

        Menu menu = menuMapper.toEntityFromMenuFetchDTO(menuFetchRequestDTO);
        Menu menuForDateAndCategory = menuService.getMenuByDateAndCategory(menu);
        return menuMapper.toResponseDTO(menuForDateAndCategory);
    }

    public MenuResponseDTO getMenuById(Long menuId) throws RecordNotFoundException {
        return menuMapper.toResponseDTO(menuService.getMenuById(menuId));
    }

    public List<MenuResponseDTO> getAllMenus() {
        return menuMapper.toResponseDTOs(menuService.getAllMenus());
    }


    public List<MenuResponseDTO> getAllMenusByCanteenId(Long canteenId, LocalDate currentDate) throws RecordNotFoundException {
        return menuMapper.toResponseDTOs(menuService.getAllMenuByCanteenId(canteenId,currentDate));
    }


    public void deleteMenuById(Long menuId) throws RecordNotFoundException {
        menuService.deleteMenuById(menuId);
    }

    public List<MenuResponseDTO> getUpcomingMenusOfAllCanteens(){
        List<Menu> menuForUpcomingDays = menuService.getUpcomingMenusOfAllCanteens();
        return menuMapper.toResponseDTOs(menuForUpcomingDays);
    }
}