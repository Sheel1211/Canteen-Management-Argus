package com.argus.cms.canteenManagement.mappers;

import com.argus.cms.canteenManagement.dto.CanteenCreateDTO;
import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import com.argus.cms.canteenManagement.entities.Canteen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CanteenMapper {

    CanteenCreateDTO canteenToCanteenDTO(Canteen canteen);

    @Mapping(target = "active", ignore = true)
    Canteen canteenDTOToCanteen(CanteenCreateDTO canteenCreateDTO);

    CanteenResponseDTO canteenToCanteenResponseDTO(Canteen canteen);

    List<CanteenResponseDTO> canteenListToCanteenResponseDTOList(List<Canteen> canteens);
}

