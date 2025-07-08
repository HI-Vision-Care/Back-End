package com.hivision.hivision.service.iservice;

public interface IChatBoxService {
    void requireConsultation(String patientID);
    void confirmConsultation(String staffID,String patientID);
}
