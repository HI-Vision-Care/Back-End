package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.response.FacilityResponse;
import com.hivision.hivision.pojo.Facility;

import java.util.List;

public interface IFacilityService {
    FacilityResponse getByFacility(String facilityID);
    List<Facility> getAll();
}
