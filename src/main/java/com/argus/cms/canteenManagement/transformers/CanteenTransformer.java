package com.argus.cms.canteenManagement.transformers;

import com.argus.cms.canteenManagement.dto.CanteenCreateDTO;
import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.mappers.CanteenMapper;
import com.argus.cms.canteenManagement.services.CanteenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CanteenTransformer {

    private final CanteenMapper canteenMapper;
    private final CanteenService canteenService;

    public CanteenResponseDTO createCanteen(CanteenCreateDTO canteenCreateDTO) {
        Canteen canteen = canteenMapper.canteenDTOToCanteen(canteenCreateDTO);
        System.out.println(canteenCreateDTO);
        canteen.setActive(false);
        Canteen createdCanteen = canteenService.saveCanteen(canteen);
        return canteenMapper.canteenToCanteenResponseDTO(createdCanteen);
    }

    public List<CanteenResponseDTO> getAllCanteens() {
        List<Canteen> canteens = canteenService.getAllCanteens();
        return canteenMapper.canteenListToCanteenResponseDTOList(canteens);
    }

    public void deleteCanteenById(Long canteenId){
        canteenService.deleteCanteenById(canteenId);
    }
}