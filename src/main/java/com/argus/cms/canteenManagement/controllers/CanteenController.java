package com.argus.cms.canteenManagement.controllers;

import com.argus.cms.canteenManagement.dto.CanteenCreateDTO;
import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import com.argus.cms.canteenManagement.transformers.CanteenTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canteens")
@RequiredArgsConstructor
public class CanteenController {

    private final CanteenTransformer canteenTransformer;

    @PostMapping
    public ResponseEntity<CanteenResponseDTO> createCanteen(@RequestBody CanteenCreateDTO canteenCreateDTO) {
        CanteenResponseDTO createdCanteen = canteenTransformer.createCanteen(canteenCreateDTO);
        return new ResponseEntity<>(createdCanteen, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CanteenResponseDTO>> getAllCanteens() {
        List<CanteenResponseDTO> canteens = canteenTransformer.getAllCanteens();
        return new ResponseEntity<>(canteens,HttpStatus.OK);
    }

    @DeleteMapping("/{canteenId}")
    public ResponseEntity<Object> deleteCanteenById(@PathVariable Long canteenId){
        canteenTransformer.deleteCanteenById(canteenId);
        return new ResponseEntity<>("Canteen Deleted Successfully!",HttpStatus.OK);
    }
}