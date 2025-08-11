package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.response.FacilityResponse;
import com.hivision.hivision.pojo.MedicalFacility;

import java.util.List;

public interface IMedicalFacilityService {
    FacilityResponse getByFacility(String facilityID);
    List<MedicalFacility> getAll();
}
