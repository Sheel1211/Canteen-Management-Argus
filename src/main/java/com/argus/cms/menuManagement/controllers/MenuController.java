package com.argus.cms.menuManagement.controllers;

import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.MenuFetchRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuForMonthRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuResponseDTO;
import com.argus.cms.menuManagement.transformers.MenuTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuTransformer menuTransformer;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CANTEEN_MANAGER')")
    public ResponseEntity<MenuResponseDTO> addMenu(@RequestBody MenuRequestDTO menuRequestDTO) throws RecordNotFoundException, DataValidationErrorException {
        MenuResponseDTO savedMenu = menuTransformer.addMenu(menuRequestDTO);
        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> getMenuById(@PathVariable Long menuId) throws RecordNotFoundException {
        MenuResponseDTO menuResponseDTO = menuTransformer.getMenuById(menuId);
        return new ResponseEntity<>(menuResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<MenuResponseDTO> getMenuByDateAndCategory(@RequestBody MenuFetchRequestDTO menuFetchRequestDTO) throws RecordNotFoundException {
        MenuResponseDTO menuResponseDTO = menuTransformer.getMenuByDateAndCategory(menuFetchRequestDTO);
        return new ResponseEntity<>(menuResponseDTO, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<MenuResponseDTO>> getAllMenus() {
        List<MenuResponseDTO> menuResponseDTOS = menuTransformer.getAllMenus();
        return new ResponseEntity<>(menuResponseDTOS, HttpStatus.OK);
    }

    @PostMapping("/menu-of-Canteen")
    public ResponseEntity<List<MenuResponseDTO>> getAllMenusByCanteenId(@RequestBody MenuForMonthRequestDTO menuForMonthRequestDTO) throws RecordNotFoundException {
        System.out.println("In controller");
        List<MenuResponseDTO> menuResponseDTOS = menuTransformer.getAllMenusByCanteenId(menuForMonthRequestDTO.getCanteenId(),menuForMonthRequestDTO.getCurrentDate());
        return new ResponseEntity<>(menuResponseDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasRole('ROLE_CANTEEN_MANAGER')")
    public ResponseEntity<String> deleteMenuById(@PathVariable Long menuId) throws RecordNotFoundException {
        menuTransformer.deleteMenuById(menuId);
        return new ResponseEntity<>("Menu deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/upcoming-menus")
    public ResponseEntity<List<MenuResponseDTO>> getUpcomingMenusOfAllCanteens(){
        return new ResponseEntity<>(menuTransformer.getUpcomingMenusOfAllCanteens(),HttpStatus.OK);
    }
}
