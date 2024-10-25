package com.argus.cms.menuManagement.transformers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.MenuRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuResponseDTO;
import com.argus.cms.menuManagement.entities.Menu;
import com.argus.cms.menuManagement.mappers.MenuMapper;
import com.argus.cms.menuManagement.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MenuTransformer {
    private final MenuMapper menuMapper;
    private final MenuService menuService;

    public MenuResponseDTO addMenu(MenuRequestDTO menuRequestDTO) {
        Menu menu = menuMapper.toEntity(menuRequestDTO);
        Menu addedMenu = menuService.addMenu(menu);
        return menuMapper.toResponseDTO(addedMenu);
    }

//
    public MenuResponseDTO getMenuById(Long menuId) throws RecordNotFoundException {
        return menuMapper.toResponseDTO(menuService.getMenuById(menuId));
    }

    public List<MenuResponseDTO> getAllMenus() {
        return menuMapper.toResponseDTOs(menuService.getAllMenus());
    }

    public void deleteMenuById(Long menuId) throws RecordNotFoundException {
        menuService.deleteMenuById(menuId);
    }
}