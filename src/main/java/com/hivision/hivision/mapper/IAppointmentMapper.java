package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.pojo.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAppointmentMapper {
    Appointment toAppointment(AppointmentRequest appointmentRequest);

    @Mapping(source = "patient.patientID", target = "patientID")
    @Mapping(source = "medicalService.serviceID", target = "serviceID")
    @Mapping(source = "doctor.doctorID", target = "doctorID")
    AppointmentDTO toAppointmentDTO(Appointment appointment);

}
