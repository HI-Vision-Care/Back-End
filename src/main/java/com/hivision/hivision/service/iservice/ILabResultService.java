package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.LabResultDTO;

import java.util.List;

public interface ILabResultService {
    List<LabResultDTO> getLabResultsByAppointmentId(String appointmentId);
}
