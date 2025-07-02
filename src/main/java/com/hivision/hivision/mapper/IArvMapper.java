package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.ArvDTO;
import com.hivision.hivision.pojo.ARV;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArvMapper {

//    @Mapping(source = "", target = "")
    List<ArvDTO> toArvDTOs(List<ARV> arvs);
}
