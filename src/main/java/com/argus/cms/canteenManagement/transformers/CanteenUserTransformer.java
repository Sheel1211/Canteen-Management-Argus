package com.argus.cms.canteenManagement.transformers;

import com.argus.cms.canteenManagement.dto.CanteenUserDTO;
import com.argus.cms.canteenManagement.entities.CanteenUser;
import com.argus.cms.canteenManagement.mappers.CanteenUserMapper;
import com.argus.cms.canteenManagement.services.CanteenUserService;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.DataValidationErrorException;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CanteenUserTransformer {

    private CanteenUserMapper canteenUserMapper;
    private CanteenUserService canteenUserService;
    private CanteenService canteenService;

    public CanteenUserDTO addManagerToCanteen(CanteenUserDTO canteenUserDTO) throws CustomException, RecordNotFoundException, DataValidationErrorException {
        CanteenUser canteenUser = canteenUserMapper.toEntity(canteenUserDTO);
        CanteenUser savedManager = canteenUserService.addCanteenManager(canteenUser);
        return canteenUserMapper.toDTO(savedManager);
    }


    public CanteenUserDTO addOwnerToCanteen(CanteenUserDTO canteenUserDTO) throws CustomException, RecordNotFoundException, DataValidationErrorException {
        CanteenUser canteenUser = canteenUserMapper.toEntity(canteenUserDTO);
        CanteenUser savedManager = canteenUserService.addCanteenOwner(canteenUser);
        return canteenUserMapper.toDTO(savedManager);
    }


    public CanteenUserDTO getManagerById(Long managerId) throws RecordNotFoundException {
        CanteenUser canteenUser = canteenUserService.findCanteenManagerById(managerId);
        return canteenUserMapper.toDTO(canteenUser);
    }

    public CanteenUserDTO toggleCanteenManagerById(CanteenUserDTO canteenUserDTO) throws CustomException, RecordNotFoundException {
        CanteenUser canteenUser = canteenUserMapper.toEntity(canteenUserDTO);
        CanteenUser approvedCanteenUser = canteenUserService.toggleCanteenManagerById(canteenUser);
        return canteenUserMapper.toDTO(canteenUser);
    }

    public void deleteCanteenManagerReqById(CanteenUserDTO canteenUserDTO) throws CustomException {
        CanteenUser canteenManagerAndCanteen = canteenUserMapper.toEntity(canteenUserDTO);
        canteenUserService.deleteCanteenManagerReqById(canteenManagerAndCanteen);

    }


}
