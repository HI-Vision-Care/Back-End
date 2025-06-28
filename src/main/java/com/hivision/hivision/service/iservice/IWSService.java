package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.payload.request.WorkShiftRequest;

import java.util.List;

public interface IWSService {
    List<WorkShiftDTO> getAll();
    String regisWS(List<WorkShiftRequest> workShifts);
}
