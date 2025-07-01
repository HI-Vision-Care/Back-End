package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.TransactionsDTO;

import java.util.List;

public interface ITransactionService {

    void transferToAppointment(String appointmentId, String accountId);
    List<TransactionsDTO> getTransactions(String accountId);
    List<TransactionsDTO> getAllTransactions();
}
