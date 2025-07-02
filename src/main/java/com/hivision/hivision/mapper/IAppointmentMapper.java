package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.payload.response.AppointmentResponse;
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



    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(source = "medicalService.name", target = "serviceName")
    AppointmentResponse toAppointmentResponse(Appointment appointment);

    List<AppointmentDTO> toAppointmentDTOs(List<Appointment> appointments);
    List<AppointmentResponse> toAppointmentResponses(List<Appointment> appointments);
}
