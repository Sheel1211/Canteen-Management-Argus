package com.argus.cms.canteenManagement.mappers;

import com.argus.cms.canteenManagement.dto.CanteenRequestDTO;
import com.argus.cms.canteenManagement.dto.CanteenResponseDTO;
import com.argus.cms.canteenManagement.entities.Canteen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface
CanteenMapper {


    @Mapping(target = "active", ignore = true)
    Canteen toEntity(CanteenRequestDTO canteenRequestDTO);

    CanteenResponseDTO toResponseDTO(Canteen canteen);

    List<CanteenResponseDTO> toResponseDTOList(List<Canteen> canteens);
}

