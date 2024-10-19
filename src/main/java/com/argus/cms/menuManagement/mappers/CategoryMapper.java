package com.argus.cms.menuManagement.mappers;

import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.menuManagement.dtos.CategoryDTO;
import com.argus.cms.menuManagement.dtos.CategoryResponseDTO;
import com.argus.cms.menuManagement.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Autowired
    private CanteenService canteenService;

    @Mapping(target = "canteen", source = "canteenId", qualifiedByName = "mapCanteenIdToCanteen")
    public abstract Category toEntity(CategoryDTO categoryDTO);

    @Mapping(target = "canteen.createdBy", source = "canteen.createdBy")
    @Mapping(target = "canteen.createdAt", source = "canteen.createdAt")
    public abstract CategoryResponseDTO toResponseDTO(Category category);

    @Mapping(target = "canteen.createdBy", source = "canteen.createdBy")
    @Mapping(target = "canteen.createdAt", source = "canteen.createdAt")
    public abstract List<CategoryResponseDTO> toResponseListDTO(List<Category> category);

    @Named("mapCanteenIdToCanteen")
    public Canteen map(Long canteenId) {
        return canteenId != null ? canteenService.getCanteenById(canteenId) : null;
    }

}
