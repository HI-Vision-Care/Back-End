package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.pojo.WorkShift;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IWSMapper {
    List<WorkShiftDTO> toListWorkShiftDTO(List<WorkShift> workShifts);
}
