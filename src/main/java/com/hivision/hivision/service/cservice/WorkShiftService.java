package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.WorkShiftStatus;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IWSMapper;
import com.hivision.hivision.payload.request.WorkShiftRequest;
import com.hivision.hivision.pojo.WorkShift;
import com.hivision.hivision.repository.IDoctorRepo;
import com.hivision.hivision.repository.IWorkShiftRepo;
import com.hivision.hivision.service.iservice.IWSService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class WorkShiftService implements IWSService {

    IWorkShiftRepo wsRepo;
    IDoctorRepo docRepo;
    IWSMapper wsMapper;

    @Override
    public List<WorkShiftDTO> getAll() {
        return wsMapper.toListWorkShiftDTO(wsRepo.findAll());
    }

    @Override
    public String regisWS(List<WorkShiftRequest> workShifts,String doctorID) {
        for (WorkShiftRequest ws : workShifts) {
            WorkShift workShift = WorkShift.builder()
                    .doctor(docRepo.findDoctorByDoctorID(doctorID))
                    .slot(ws.getSlot())
                    .date(ws.getDate())
                    .startTime(ws.getStartTime())
                    .endTime(ws.getEndTime())
                    .status(WorkShiftStatus.AVAILABLE)
                    .build();
            wsRepo.save(workShift);
        }
        return "Work shift successfully";
    }

    @Override
    public List<WorkShiftDTO> getShiftsForWeek(LocalDate dateInWeek, String doctorId) {
        LocalDate startOfWeek = dateInWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDateTime startDateTime = LocalDateTime.from(startOfWeek.atStartOfDay());

        LocalDateTime endDateTime = LocalDateTime.from(startDateTime.plusDays(7));

        // 2. Gọi phương thức repository tương ứng (phần này vẫn giữ nguyên vì đã hợp lý)
        List<WorkShift> workShifts;
        if (StringUtils.hasText(doctorId)) {
            // Kiểm tra sự tồn tại của bác sĩ trước khi truy vấn
            if (!docRepo.existsByDoctorID(doctorId)) {
                throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
            }
            workShifts = wsRepo.findShiftsBetweenDatesForDoctor(doctorId, startDateTime, endDateTime);
        } else {
            workShifts = wsRepo.findShiftsBetweenDates(startDateTime, endDateTime);
        }

        // 3. Map kết quả sang DTO và trả về
        return wsMapper.toListWorkShiftDTO(workShifts);
    }

    @Override
    public List<WorkShiftDTO> getShiftsByDoctorId(String doctorId) {

        boolean exist = docRepo.existsByDoctorID(doctorId);
        if (!exist) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        List<WorkShift> workShifts = wsRepo.findShiftsByDoctorId(doctorId);
        if (workShifts.isEmpty()) {
            throw new AppException(ErrorCode.WORK_SHIFT_NOT_FOUND);
        }
        return wsMapper.toListWorkShiftDTO(workShifts);
    }

    @Override
    public void finishWorkShift(int wsID) {
        WorkShift workShift = wsRepo.findById(wsID)
                .orElseThrow(() -> new AppException(ErrorCode.WORK_SHIFT_NOT_FOUND));

        workShift.setStatus(WorkShiftStatus.FINISHED);
        wsRepo.save(workShift);
    }
}
