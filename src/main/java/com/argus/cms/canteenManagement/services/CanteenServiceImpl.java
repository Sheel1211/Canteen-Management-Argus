package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.repositories.CanteenRepository;
import com.argus.cms.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CanteenServiceImpl implements CanteenService {

    private final CanteenRepository canteenRepository;

    @Override
    public Canteen saveCanteen(Canteen canteen) {
        return canteenRepository.save(canteen);
    }

    @Override
    public List<Canteen> getAllCanteens() {
        return canteenRepository.findAll();
    }

    @Override
    public Canteen getCanteenById(Long id) {
        return canteenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Canteen not found"));
    }

    @Override
    public void deleteCanteenById(Long canteenId) {
        canteenRepository.findById(canteenId).orElseThrow(() -> new EntityNotFoundException("Canteen not found"));
        canteenRepository.deleteById(canteenId);
    }
}