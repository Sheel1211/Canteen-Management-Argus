package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.repositories.CanteenRepository;
import com.argus.cms.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CanteenServiceImpl implements CanteenService {

    private final CanteenRepository canteenRepository;

    @Override
    @Transactional
    public Canteen saveCanteen(Canteen canteen) {
        return canteenRepository.save(canteen);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Canteen> getAllCanteens() {
        return canteenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Canteen getCanteenById(Long id) {
        return canteenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Canteen not found"));
    }

    @Override
    @Transactional
    public void deleteCanteenById(Long canteenId) {
        Canteen canteen = this.getCanteenById(canteenId);
        if(canteen.getIsDeleted()){
            throw new EntityNotFoundException("Canteen doesn't exist with id " + canteenId);
        }
        canteen.setIsDeleted(true);
    }

    @Override
    @Transactional
    public Canteen updateCanteenStatusById(Long canteenId) {
        Canteen fetchedCanteen =this.getCanteenById(canteenId);
        fetchedCanteen.setActive(!fetchedCanteen.isActive());
        return fetchedCanteen;
    }

    @Override
    @Transactional
    public Canteen updateCanteenNameById(Long canteenId, Canteen canteen) {
        Canteen fetchedCanteen = this.getCanteenById(canteenId);
        fetchedCanteen.setName(canteen.getName());
        return fetchedCanteen;
    }
}