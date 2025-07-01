package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.TransactionsDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.ITransactionsMapper;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Transactions;
import com.hivision.hivision.pojo.Wallet;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.ITransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionService implements ITransactionService {

    IAccountRepo accountRepo;
    IMedicalServiceRepo medicalServiceRepo;
    IAppointmentRepo appointmentRepo;
    IWalletRepo walletRepo;
    ITransactionsRepo transactionsRepo;
    ITransactionsMapper transactionsMapper;

    @Override
    public void transferToAppointment(String appointmentId, String accountId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));


    }

    @Override
    public List<TransactionsDTO> getTransactions(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Wallet wallet = walletRepo.findWalletByAccount(account);
        if (wallet == null) {
            throw new AppException(ErrorCode.WALLET_NOT_FOUND);
        }
        List<Transactions> transactions = transactionsRepo.findByWallet(wallet);
        return transactionsMapper.toTransactionsDTO(transactions);
    }

    @Override
    public List<TransactionsDTO> getAllTransactions() {
        List<Transactions> transactions = transactionsRepo.findAll();
        if (transactions.isEmpty()) {
            throw new AppException(ErrorCode.TRANSACTION_NOT_FOUND);
        }
        return transactionsMapper.toTransactionsDTO(transactions);
    }
}
