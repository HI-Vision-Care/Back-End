package com.hivision.hivision.mapper;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;
import org.mapstruct.Mapper;

@Mapper
public interface IPrescriptionMapper {
    ARV toARV(PrescriptionARV prescriptionARV);
}
