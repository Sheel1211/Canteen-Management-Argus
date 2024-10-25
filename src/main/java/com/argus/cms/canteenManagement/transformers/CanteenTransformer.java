package com.argus.cms.canteenManagement.transformers;

import com.argus.cms.canteenManagement.dto.CanteenRequestDTO;
import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.mappers.CanteenMapper;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CanteenTransformer {

    private final CanteenMapper canteenMapper;
    private final CanteenService canteenService;

    public CanteenResponseDTO createCanteen(CanteenRequestDTO canteenRequestDTO) {
        Canteen canteen = canteenMapper.toEntity(canteenRequestDTO);
//        System.out.println(canteenRequestDTO);
        canteen.setActive(false);
        Canteen createdCanteen = canteenService.saveCanteen(canteen);
        return canteenMapper.toResponseDTO(createdCanteen);
    }

    public List<CanteenResponseDTO> getAllCanteens() {
        List<Canteen> canteens = canteenService.getAllCanteens();
        return canteenMapper.toResponseDTOList(canteens);
    }

    public void deleteCanteenById(Long canteenId) throws RecordNotFoundException {
        canteenService.deleteCanteenById(canteenId);
    }

    public CanteenResponseDTO updateCanteenStatusById(Long canteenId) throws RecordNotFoundException {
        Canteen canteen = canteenService.updateCanteenStatusById(canteenId);
        return canteenMapper.toResponseDTO(canteen);
    }

    public CanteenResponseDTO updateCanteenNameById(Long canteenId,CanteenRequestDTO canteenRequestDTO) throws RecordNotFoundException {
        Canteen canteen = canteenMapper.toEntity(canteenRequestDTO);
        Canteen updatedCanteen = canteenService.updateCanteenNameById(canteenId,canteen);
        return canteenMapper.toResponseDTO(updatedCanteen);
    }



}