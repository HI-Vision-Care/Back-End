package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.pojo.LabResult;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface ILabResultMapper {
    List<LabResultDTO> toLabResultDTO(List<LabResult> results);
}
