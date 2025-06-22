package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.pojo.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IAppointmentMapper {
    Appointment toAppointment(AppointmentRequest appointmentRequest);
    AppointmentDTO toAppointmentDTO(Appointment appointment);

}
