package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.TreatmentDTO;
import com.hivision.hivision.pojo.Treatment;
import org.mapstruct.Mapper;

@Mapper
public interface ITreatmentMapper {
    TreatmentDTO toTreatmentDTO(Treatment treatment);
}
