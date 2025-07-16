package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.payload.request.WorkShiftRequest;
import com.hivision.hivision.pojo.WorkShift;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface IWSService {
    List<WorkShiftDTO> getAll();
    String regisWS(List<WorkShiftRequest> workShifts,String doctorID);
    List<WorkShiftDTO> getShiftsForWeek(LocalDate dateInWeek, String doctorId);
    List<WorkShiftDTO> getShiftsByDoctorId(String doctorId);
    void finishWorkShift(int wsID);
}
