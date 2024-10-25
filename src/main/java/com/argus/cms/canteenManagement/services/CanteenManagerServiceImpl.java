package com.argus.cms.canteenManagement.services;

import com.argus.cms.canteenManagement.entities.CanteenManager;
import com.argus.cms.canteenManagement.repositories.CanteenManagerRepository;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CanteenManagerServiceImpl implements CanteenManagerService {

    private CanteenManagerRepository canteenManagerRepository;

    @Override
    @Transactional
    public CanteenManager addCanteenManager(CanteenManager canteenManager) throws CustomException {
        boolean isUserRoleCanteenManager = canteenManager.getManager().getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_CANTEEN_MANAGER"));

        if (!isUserRoleCanteenManager) {
            throw new CustomException("You are not a Canteen Manager!",HttpStatus.FORBIDDEN);
        }
        CanteenManager isEntryAlreadyExisting = canteenManagerRepository.findByManagerAndCanteen(canteenManager.getManager(),canteenManager.getCanteen()).orElse(null);

        if(isEntryAlreadyExisting != null){
            throw new CustomException("You have already applied for the same canteen", HttpStatus.FORBIDDEN);
        }

        CanteenManager alreadyExistingManager = canteenManagerRepository.findByManagerAndIsActiveTrue(canteenManager.getManager()).orElse(null);
        boolean isCanteenManagerActiveForAnotherCanteen = alreadyExistingManager != null;

        if(isCanteenManagerActiveForAnotherCanteen){
            throw new CustomException("You are already active for some other canteen", HttpStatus.FORBIDDEN);
        }
        canteenManagerRepository.save(canteenManager);
        return canteenManager;
    }

    @Override
    @Transactional(readOnly = true)
    public CanteenManager findCanteenManagerById(Long canteenManagerId) throws RecordNotFoundException {
        return canteenManagerRepository.findById(canteenManagerId).orElseThrow(()-> new RecordNotFoundException("No Canteen Manager found"));
    }

    @Override
    @Transactional
    public CanteenManager toggleCanteenManagerById(CanteenManager canteenManager) throws RecordNotFoundException {
        CanteenManager isEntryAlreadyExisting = canteenManagerRepository.findByManagerAndCanteen(canteenManager.getManager(),canteenManager.getCanteen()).orElse(null);
        if(isEntryAlreadyExisting == null){
            throw new RecordNotFoundException("No Canteen Manager found");
        }
        isEntryAlreadyExisting.setActive(!isEntryAlreadyExisting.isActive());
        return isEntryAlreadyExisting;
    }

    @Override
    @Transactional
    public void deleteCanteenManagerReqById(CanteenManager canteenManager) throws CustomException {
        CanteenManager isEntryAlreadyExisting = canteenManagerRepository.findByManagerAndCanteen(canteenManager.getManager(),canteenManager.getCanteen()).orElseThrow(()-> new CustomException("No Canteen Manager found",HttpStatus.NOT_FOUND));
        if(isEntryAlreadyExisting.getIsDeleted())
        {
            throw new CustomException("Record Not Found",HttpStatus.NOT_FOUND);
        }
        isEntryAlreadyExisting.setIsDeleted(true);
    }

}
