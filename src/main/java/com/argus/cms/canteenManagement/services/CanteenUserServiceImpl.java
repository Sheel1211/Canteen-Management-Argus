package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.canteenManagement.repositories.CanteenUserRepository;
import com.argus.cms.canteenManagement.validation.CanteenUserValidator;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CanteenUserServiceImpl implements CanteenUserService {

    private CanteenUserRepository canteenUserRepository;
    private CanteenUserValidator canteenUserValidator;

    @Override
    @Transactional
    public CanteenUser addCanteenManager(CanteenUser canteenUser) throws CustomException, RecordNotFoundException, DataValidationErrorException {
//        boolean isUserRoleCanteenManager = canteenManager.getManager().getRoles().stream()
//                .anyMatch(role -> role.getName().equals("ROLE_CANTEEN_MANAGER"));
//
//        if (!isUserRoleCanteenManager) {
//            throw new CustomException("You are not a Canteen Manager!",HttpStatus.FORBIDDEN);
//        }
//        CanteenManager isEntryAlreadyExisting = canteenManagerRepository.findByManagerAndCanteen(canteenManager.getManager(),canteenManager.getCanteen()).orElse(null);
//
//        if(isEntryAlreadyExisting != null){
//            throw new CustomException("You have already applied for the same canteen", HttpStatus.FORBIDDEN);
//        }
//
//        CanteenManager alreadyExistingManager = canteenManagerRepository.findByManagerAndIsActiveTrue(canteenManager.getManager()).orElse(null);
//        boolean isCanteenManagerActiveForAnotherCanteen = alreadyExistingManager != null;
//
//        if(isCanteenManagerActiveForAnotherCanteen){
//            throw new CustomException("You are already active for some other canteen", HttpStatus.FORBIDDEN);
//        }

        canteenUserValidator.validateCreateCanteenManager(canteenUser,this);
        canteenUserRepository.save(canteenUser);
        return canteenUser;
    }

    @Override
    @Transactional(readOnly = true)
    public CanteenUser findCanteenManagerById(Long canteenManagerId) throws RecordNotFoundException {
        return canteenUserRepository.findById(canteenManagerId).orElseThrow(()-> new RecordNotFoundException("No Canteen Manager found"));
    }

    @Override
    @Transactional
    public CanteenUser toggleCanteenManagerById(CanteenUser canteenUser) throws RecordNotFoundException {
        CanteenUser isEntryAlreadyExisting = canteenUserRepository.findByUserAndCanteen(canteenUser.getUser(), canteenUser.getCanteen()).orElse(null);
        if(isEntryAlreadyExisting == null){
            throw new RecordNotFoundException("No Canteen Manager found");
        }
        isEntryAlreadyExisting.setActive(!isEntryAlreadyExisting.isActive());
        return isEntryAlreadyExisting;
    }

    @Override
    @Transactional
    public void deleteCanteenManagerReqById(CanteenUser canteenUser) throws CustomException {
        CanteenUser isEntryAlreadyExisting = canteenUserRepository.findByUserAndCanteen(canteenUser.getUser(), canteenUser.getCanteen()).orElseThrow(()-> new CustomException("No Canteen Manager found",HttpStatus.NOT_FOUND));
        if(isEntryAlreadyExisting.getIsDeleted())
        {
            throw new CustomException("Record Not Found",HttpStatus.NOT_FOUND);
        }
        isEntryAlreadyExisting.setIsDeleted(true);
    }

    @Override
    public CanteenUser addCanteenOwner(CanteenUser canteenUser) throws CustomException {
        canteenUser.setOwner(true);
        return canteenUserRepository.save(canteenUser);
    }

}
