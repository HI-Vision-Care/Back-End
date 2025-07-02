package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.MedicalRecordDTO;
import com.hivision.hivision.payload.request.MedicalRecordRequest;
import com.hivision.hivision.pojo.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IMedicalRecordMapper {
    @Mapping(source = "appointment.appointmentID", target = "appointmentId")
     MedicalRecordDTO toMedicalRecordDTO(MedicalRecord medicalRecord);
}
