package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.dto.TestItemDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.repository.IMedicalServiceRepo;
import com.hivision.hivision.repository.IServiceTestItemRepo;
import com.hivision.hivision.service.iservice.IMedicalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ProvisionService implements IMedicalService {
    IMedicalServiceRepo medicalServiceRepo;
    IServiceTestItemRepo serviceTestItemRepo;

    @Override
    public List<MedicalService> getAllMedicalServices() {
        return medicalServiceRepo.findAll();
    }

    @Override
    public MedicalServiceDTO getMedicalServiceById(Long id) {
//        return medicalServiceRepo.findMedicalServiceByServiceID(id);
        MedicalService service = medicalServiceRepo.findByServiceID(id)
                .orElseThrow(() -> new AppException(ErrorCode.MEDICAL_SERVICE_NOT_FOUND));

        // Lấy testItem theo ServiceID
        List<TestItemDTO> testItems = serviceTestItemRepo.findByMedicalService_ServiceID(service.getServiceID())
                .stream()
                .map(sti -> TestItemDTO.builder()
//                        .testID(sti.getTestItem().getTestID())
                        .testName(sti.getTestItem().getTestName())
                        .testDescription(sti.getTestItem().getTestDescription())
                        .unit(sti.getTestItem().getUnit())
                        .referenceRange(sti.getTestItem().getReferenceRange())
                        .build()
                )
                .toList();

        return MedicalServiceDTO.builder()
                .serviceID(service.getServiceID())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .type(service.getType())
                .specialty(service.getSpecialty())
                .isActive(service.getIsActive())
                .isRequireDoctor(service.getIsRequireDoctor())
                .isOnline(service.getIsOnline())
                .createAt(service.getCreateAt())
                .img(service.getImg())
                .testItems(testItems)
                .build();
    }

    @Override
    public List<MedicalServiceDTO> getAllServicesWithTests() {
        List<MedicalService> services = medicalServiceRepo.findByIsActiveTrue();

        return services.stream().map(service -> {
            // Lấy testItem theo ServiceID
            List<TestItemDTO> testItems = serviceTestItemRepo.findTestsByServiceId(service.getServiceID())
                    .stream()
                    .map(test -> TestItemDTO.builder()
//                            .testID(test.getTestID())
                            .testName(test.getTestName())
                            .testDescription(test.getTestDescription())
                            .unit(test.getUnit())
                            .referenceRange(test.getReferenceRange())
                            .build())
                    .toList();

            // Chuyển đổi MedicalService sang MedicalServiceDTO
            return MedicalServiceDTO.builder()
                    .serviceID(service.getServiceID())
                    .name(service.getName())
                    .description(service.getDescription())
                    .price(service.getPrice())
                    .type(service.getType())
                    .specialty(service.getSpecialty())
                    .isActive(service.getIsActive())
                    .isRequireDoctor(service.getIsRequireDoctor())
                    .isOnline(service.getIsOnline())
                    .createAt(service.getCreateAt())
                    .img(service.getImg())
                    .testItems(testItems)
                    .build();
        }).toList();
    }

}
