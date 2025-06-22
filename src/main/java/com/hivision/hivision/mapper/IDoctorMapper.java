package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.pojo.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IDoctorMapper {
    DoctorDTO toDoctorDTO(Doctor doctor);
    List<DoctorDTO> toDoctorDTO(List<Doctor> doctors);

}
