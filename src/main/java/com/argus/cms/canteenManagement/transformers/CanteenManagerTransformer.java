package com.argus.cms.canteenManagement.transformers;

import com.argus.cms.canteenManagement.dto.CanteenManagerDTO;
import com.argus.cms.canteenManagement.entities.CanteenManager;
import com.argus.cms.canteenManagement.mappers.CanteenManagerMapper;
import com.argus.cms.canteenManagement.services.CanteenManagerService;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.CustomException;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CanteenManagerTransformer {

    private CanteenManagerMapper canteenManagerMapper;
    private CanteenManagerService canteenManagerService;
    private CanteenService canteenService;

    public CanteenManagerDTO addManagerToCanteen(CanteenManagerDTO canteenManagerDTO) throws CustomException {
        CanteenManager canteenManager = canteenManagerMapper.toEntity(canteenManagerDTO);
        CanteenManager savedManager = canteenManagerService.addCanteenManager(canteenManager);
        return canteenManagerMapper.toDTO(savedManager);
    }

    public CanteenManagerDTO getManagerById(Long managerId) throws RecordNotFoundException {
        CanteenManager canteenManager = canteenManagerService.findCanteenManagerById(managerId);
        return canteenManagerMapper.toDTO(canteenManager);
    }

    public CanteenManagerDTO toggleCanteenManagerById(CanteenManagerDTO canteenManagerDTO) throws CustomException, RecordNotFoundException {
        CanteenManager canteenManager = canteenManagerMapper.toEntity(canteenManagerDTO);
        CanteenManager approvedCanteenManager = canteenManagerService.toggleCanteenManagerById(canteenManager);
        return canteenManagerMapper.toDTO(canteenManager);
    }

    public void deleteCanteenManagerReqById(CanteenManagerDTO canteenManagerDTO) throws CustomException {
        CanteenManager canteenManagerAndCanteen = canteenManagerMapper.toEntity(canteenManagerDTO);
        canteenManagerService.deleteCanteenManagerReqById(canteenManagerAndCanteen);

    }


}
