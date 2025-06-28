package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.pojo.LabResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILabResultMapper {

    @Mapping(source = "medicalRecord.recordId", target = "recordId")
    LabResultDTO toLabResultDTO(LabResult result); //ánh xạ từng đối tượng LabResult sang LabResultDTO

    List<LabResultDTO> toLabResultDTO(List<LabResult> results);
}
