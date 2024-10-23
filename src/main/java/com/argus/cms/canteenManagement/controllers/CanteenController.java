package com.argus.cms.canteenManagement.controllers;

import com.argus.cms.canteenManagement.dto.CanteenRequestDTO;
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
    public ResponseEntity<CanteenResponseDTO> createCanteen(@RequestBody CanteenRequestDTO canteenRequestDTO) {
        CanteenResponseDTO createdCanteen = canteenTransformer.createCanteen(canteenRequestDTO);
        return new ResponseEntity<>(createdCanteen, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CanteenResponseDTO>> getAllCanteens() {
        List<CanteenResponseDTO> canteens = canteenTransformer.getAllCanteens();
        return new ResponseEntity<>(canteens,HttpStatus.OK);
    }

    @PutMapping("/status/{canteenId}")
    public ResponseEntity<CanteenResponseDTO> updateCanteenStatusById(@PathVariable Long canteenId) {
        CanteenResponseDTO updatedCanteen = canteenTransformer.updateCanteenStatusById(canteenId);
        return new ResponseEntity<>(updatedCanteen,HttpStatus.OK);
    }

    @PutMapping("/{canteenId}")
    public ResponseEntity<CanteenResponseDTO> updateCanteenNameById(@PathVariable Long canteenId,@RequestBody CanteenRequestDTO canteenRequestDTO) {
        CanteenResponseDTO updatedCanteen = canteenTransformer.updateCanteenNameById(canteenId,canteenRequestDTO);
        return new ResponseEntity<>(updatedCanteen,HttpStatus.OK);
    }

    @DeleteMapping("/{canteenId}")
    public ResponseEntity<Object> deleteCanteenById(@PathVariable Long canteenId){
        canteenTransformer.deleteCanteenById(canteenId);
        return new ResponseEntity<>("Canteen Deleted Successfully!",HttpStatus.OK);
    }
}