package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.PreArvRequest;
import com.hivision.hivision.pojo.PrescriptionARV;

import java.util.List;

public interface IPreArvService {
    List<PrescriptionARV> addPreArv(PreArvRequest request);
}
