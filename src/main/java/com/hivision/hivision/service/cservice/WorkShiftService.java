package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.enums.ErrorCode;
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
import java.time.Instant;
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
                    .status("Available")
                    .build();
            wsRepo.save(workShift);
        }
        return "Work shift successfully";
    }

    @Override
    public List<WorkShiftDTO> getShiftsForWeek(LocalDate dateInWeek, String doctorId) {
        // 1. Tính toán ngày bắt đầu và kết thúc của tuần
        // Giả sử tuần bắt đầu từ Thứ Hai (Monday)
        LocalDate startOfWeek = dateInWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        // Nếu bạn muốn tuần bắt đầu từ Chủ Nhật (Sunday)
        // LocalDate startOfWeek = dateInWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        LocalDateTime startDateTime = startOfWeek.atStartOfDay(); // 00:00:00 của ngày đầu tuần
        LocalDateTime  endDateTime = startDateTime.plusDays(7);   // 00:00:00 của ngày đầu tuần tiếp theo

        // 2. Gọi phương thức repository tương ứng
        if (StringUtils.hasText(doctorId)) {
            // Nếu có doctorId, gọi phương thức có lọc
            if (!docRepo.existsByDoctorID(doctorId)) {
                throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
            }
            return wsMapper.toListWorkShiftDTO(wsRepo.findShiftsBetweenDatesForDoctor(doctorId, startDateTime, endDateTime));
        }
            else {
            // Nếu không, gọi phương thức không lọc
            return wsMapper.toListWorkShiftDTO(wsRepo.findShiftsBetweenDates(startDateTime, endDateTime));
        }
    }
}
