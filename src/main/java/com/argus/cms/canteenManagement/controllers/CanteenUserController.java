package com.argus.cms.canteenManagement.controllers;

import com.argus.cms.canteenManagement.dto.CanteenUserDTO;
import com.argus.cms.canteenManagement.transformers.CanteenUserTransformer;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/canteen-user")
@RestController
public class CanteenUserController {

    private CanteenUserTransformer canteenUserTransformer;

    @PostMapping("/manager")
    @PreAuthorize("hasAnyRole('ROLE_CANTEEN_MANAGER','ROLE_CANTEEN_OWNER')")
    public ResponseEntity<CanteenUserDTO> addManagerToCanteen(@RequestBody CanteenUserDTO canteenUserDTO) throws CustomException, RecordNotFoundException, DataValidationErrorException {
        CanteenUserDTO savedCanteenUserDTO = canteenUserTransformer.addManagerToCanteen(canteenUserDTO);
        return new ResponseEntity<>(savedCanteenUserDTO,HttpStatus.CREATED);
    }

    @PostMapping("/owner")
    @PreAuthorize("hasRole('ROLE_CANTEEN_OWNER')")
    public ResponseEntity<String> ownerRequestToGetCanteenOwnership(@RequestBody CanteenUserDTO canteenUserDTO) throws CustomException, RecordNotFoundException, DataValidationErrorException {
        CanteenUserDTO savedCanteenUserDTO = canteenUserTransformer.addOwnerToCanteen(canteenUserDTO);
        return new ResponseEntity<>("Successfully Requested", HttpStatus.CREATED);
    }


    @GetMapping("/manager/{managerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CanteenUserDTO> getManagerById(@PathVariable Long managerId) throws RecordNotFoundException {
        return new ResponseEntity<>(canteenUserTransformer.getManagerById(managerId),HttpStatus.OK);
    }

    @PutMapping("/manager")
    @PreAuthorize("hasAnyRole('ROLE_CANTEEN_OWNER', 'ROLE_ADMIN')")
    public ResponseEntity<CanteenUserDTO> toggleCanteenManagerById(@RequestBody CanteenUserDTO canteenUserDTO) throws CustomException, RecordNotFoundException {
        return new ResponseEntity<>(canteenUserTransformer.toggleCanteenManagerById(canteenUserDTO),HttpStatus.OK);
    }

    @DeleteMapping("/manager")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCanteenManagerReqById(@RequestBody CanteenUserDTO canteenUserDTO) throws CustomException {
        canteenUserTransformer.deleteCanteenManagerReqById(canteenUserDTO);
        return new ResponseEntity<>("Canteen Manager Deleted Successfully!",HttpStatus.OK);
    }
}
