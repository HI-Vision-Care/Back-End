package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.ArvDTO;
import com.hivision.hivision.payload.response.ArvResponse;

import java.util.List;

public interface IArvService {
    List<ArvResponse> getARVs();
}
