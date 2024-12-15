package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.repositories.CanteenRepository;
import com.argus.cms.canteenManagement.validation.CanteenValidator;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CanteenServiceImpl implements CanteenService {

    private final CanteenRepository canteenRepository;
    private final CanteenValidator canteenValidator;
    private final UserService userService;

    @Override
    @Transactional
    public Canteen saveCanteen(Canteen canteen) throws RecordNotFoundException, DataValidationErrorException {
        canteenValidator.validateCreateCanteen(canteen,this);
        return canteenRepository.save(canteen);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Canteen> getAllCanteens() {
        return canteenRepository.findAll();
    }

    @Override
    @Transactional
    public Canteen ownerRequestToGetCanteenOwnership(Long canteenId, Long userId) throws RecordNotFoundException {
        Canteen canteen = canteenRepository.findById(canteenId).orElseThrow(() -> new RecordNotFoundException("Canteen not found"));
        Users canteenOwner = userService.findUserById(userId);
        return canteen;
    }

    @Override
    @Transactional(readOnly = true)
    public Canteen getCanteenById(Long id) throws RecordNotFoundException {
        return canteenRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Canteen not found"));
    }

    @Override
    @Transactional
    public void deleteCanteenById(Long canteenId) throws RecordNotFoundException {
        Canteen canteen = this.getCanteenById(canteenId);
        if(canteen.getIsDeleted()){
            throw new RecordNotFoundException("Canteen doesn't exist with id " + canteenId);
        }
        canteen.setIsDeleted(true);
    }

    @Override
    @Transactional
    public Canteen updateCanteenStatusById(Long canteenId) throws RecordNotFoundException {
        Canteen fetchedCanteen = this.getCanteenById(canteenId);
        fetchedCanteen.setActive(!fetchedCanteen.isActive());
        return fetchedCanteen;
    }

    @Override
    @Transactional
    public Canteen updateCanteenNameById(Long canteenId, Canteen canteen) throws RecordNotFoundException {
        Canteen fetchedCanteen = this.getCanteenById(canteenId);
        fetchedCanteen.setName(canteen.getName());
        return fetchedCanteen;
    }

    @Override
    public Canteen findByUser(Users user) {
        return canteenRepository.findByCanteenUsers(user);
    }
}