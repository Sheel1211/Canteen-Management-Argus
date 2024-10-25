package com.argus.cms.menuManagement.controllers;

import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.menuManagement.dtos.MenuRequestDTO;
import com.argus.cms.menuManagement.dtos.MenuResponseDTO;
import com.argus.cms.menuManagement.transformers.MenuTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuTransformer menuTransformer;

    @PostMapping
    public ResponseEntity<MenuResponseDTO> addMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
        MenuResponseDTO savedMenu = menuTransformer.addMenu(menuRequestDTO);
        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> getMenuById(@PathVariable Long menuId) throws RecordNotFoundException {
        MenuResponseDTO menuResponseDTO = menuTransformer.getMenuById(menuId);
        return new ResponseEntity<>(menuResponseDTO, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<MenuResponseDTO>> getAllMenus() {
        List<MenuResponseDTO> menuResponseDTOS = menuTransformer.getAllMenus();
        return new ResponseEntity<>(menuResponseDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenuById(@PathVariable Long menuId) throws RecordNotFoundException {
        menuTransformer.deleteMenuById(menuId);
        return new ResponseEntity<>("Menu deleted successfully!", HttpStatus.OK);
    }
}
