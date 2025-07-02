package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.TransactionsDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.TransactionsEnum;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.ITransactionsMapper;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.ITransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.time.Instant;

import java.time.LocalDateTime;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionService implements ITransactionService {

    IAccountRepo accountRepo;
    IPatientRepo patientRepo;
    IMedicalServiceRepo medicalServiceRepo;
    IAppointmentRepo appointmentRepo;
    IWalletRepo walletRepo;
    ITransactionsRepo transactionsRepo;
    ITransactionsMapper transactionsMapper;

    @Override
    public void transferToAppointment(String appointmentId, String accountId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Wallet wallet = walletRepo.findWalletByAccount(account);
        if (wallet == null) {
            throw new AppException(ErrorCode.WALLET_NOT_FOUND);
        }

        double price = appointment.getMedicalService().getPrice();

        if (wallet.getBalance() < price) {
            throw new AppException(ErrorCode.INSUFFICIENT_BALANCE);
        }
        wallet.setBalance(wallet.getBalance() - price);
        walletRepo.save(wallet);

        Transactions transaction = Transactions.builder()
                .wallet(wallet)
                .amount(price)
                .description("Payment for appointment: " + appointment.getAppointmentID())
                .date(Instant.now())
                .type(TransactionsEnum.TRANSFER)
                .status("COMPLETED")
                .build();
        transactionsRepo.save(transaction);

        appointment.setPaymentStatus("PAID");
        appointmentRepo.save(appointment);


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

    @Override
    public void rollBack(String patientID) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        Account account = patient.getAccount();
        Wallet wallet = walletRepo.findWalletByAccount(account);
        wallet.setBalance(wallet.getBalance());
        walletRepo.save(wallet);
//        Transactions transaction = Transactions.builder()
//                .amount(deposit)
//                .date(LocalDateTime.now())
//                .description("")
//                .type(TransactionsEnum.REFUND)
//                .status("COMPLETED")
//                .description("Refund for bidder")
//                .wallet(wallet)
//                .build();
//        transactionRepo.save(transaction);
    }
}
