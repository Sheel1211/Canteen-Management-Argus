package com.argus.cms.canteenManagement.controllers;

import com.argus.cms.canteenManagement.dto.CanteenManagerDTO;
import com.argus.cms.canteenManagement.transformers.CanteenManagerTransformer;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/canteen-manager")
@RestController
public class CanteenManagerController {

    private CanteenManagerTransformer canteenManagerTransformer;

    @PostMapping
    @PreAuthorize("!hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<CanteenManagerDTO> addManagerToCanteen(@RequestBody CanteenManagerDTO canteenManagerDTO) throws CustomException {
        CanteenManagerDTO savedCanteenManagerDTO = canteenManagerTransformer.addManagerToCanteen(canteenManagerDTO);
        return new ResponseEntity<>(savedCanteenManagerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{managerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanteenManagerDTO> getManagerById(@PathVariable Long managerId) throws RecordNotFoundException {
        return new ResponseEntity<>(canteenManagerTransformer.getManagerById(managerId),HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanteenManagerDTO> toggleCanteenManagerById(@RequestBody CanteenManagerDTO canteenManagerDTO) throws CustomException, RecordNotFoundException {
        return new ResponseEntity<>(canteenManagerTransformer.toggleCanteenManagerById(canteenManagerDTO),HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCanteenManagerReqById(@RequestBody CanteenManagerDTO canteenManagerDTO) throws CustomException {
        canteenManagerTransformer.deleteCanteenManagerReqById(canteenManagerDTO);   
        return new ResponseEntity<>("Canteen Manager Deleted Successfully!",HttpStatus.OK);
    }
}
