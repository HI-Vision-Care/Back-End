package com.hivision.hivision.service;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.mapper.IWSMapper;
import com.hivision.hivision.payload.request.WorkShiftRequest;
import com.hivision.hivision.pojo.WorkShift;
import com.hivision.hivision.repository.IWorkShiftRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class WorkShiftService {

    IWorkShiftRepository wsRepo;
    IWSMapper wsMapper;
    public List<WorkShiftDTO> getAll() {
        return wsMapper.toListWorkShiftDTO(wsRepo.findAll());
    }

    public String regisWS(List<WorkShiftRequest> workShifts) {
        for (WorkShiftRequest ws : workShifts) {
            WorkShift workShift = WorkShift.builder()
                    .slot(ws.getSlot())
                    .date(ws.getDate())
                    .startTime(ws.getStartTime())
                    .endTime(ws.getEndTime())
                    .build();
            wsRepo.save(workShift);
        }
        return "Work shift successfully";
    }
}
