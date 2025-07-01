package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.payload.request.DoctorRequest;
import com.hivision.hivision.pojo.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface IDoctorMapper {

    @Mapping(source = "account.avatar", target = "avatar")
    DoctorDTO toDoctorDTO(Doctor doctor);
    List<DoctorDTO> toDoctorDTO(List<Doctor> doctors);
    void updateDoctor(@MappingTarget Doctor doctor, DoctorRequest request);
}
