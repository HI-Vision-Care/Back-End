package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.FacilityDTO;
import com.hivision.hivision.pojo.Facility;
import org.mapstruct.Mapper;

@Mapper
public interface IFacilityMapper {
    FacilityDTO toFacilityDTO(Facility facility); // Chuyển Facility thành FacilityDTO
}
