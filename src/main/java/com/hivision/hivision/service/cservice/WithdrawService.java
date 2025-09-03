package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.WithdrawDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.TransactionsEnum;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IWithdrawMapper;
import com.hivision.hivision.payload.request.RejectWithdrawRequest;
import com.hivision.hivision.payload.request.WithdrawRequest;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IWithdrawService;
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
public class WithdrawService implements IWithdrawService {
    IAccountRepo accountRepo;
    IWalletRepo walletRepo;
    ITransactionsRepo transactionsRepo;
    IWithdrawRepo withdrawRepo;
    IWithdrawMapper withdrawMapper;
    IStaffRepo staffRepo;


    @Override
    public WithdrawDTO createWithdraw(WithdrawRequest request, String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));

        Withdraw withdraw = Withdraw.builder()
                .amount(request.getAmount())
                .accountName(request.getAccountName())
                .accountNumber(request.getAccountNumber())
                .bankName(request.getBankName())
                .status("PENDING")
                .withdrawDate(LocalDateTime.now())
                .account(account)
                .build();

        return withdrawMapper.toWithdrawDTO(withdrawRepo.save(withdraw));
    }

    @Override
    public WithdrawDTO approveWithdraw(Long withdrawId, String staffId) {
        Withdraw withdraw = withdrawRepo.findById(withdrawId)
                .orElseThrow(()->new AppException(ErrorCode.WITHDRAW_NOT_FOUND));
        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(()->new AppException(ErrorCode.STAFF_NOT_FOUND));

        Wallet wallet = walletRepo.findWalletByAccount(withdraw.getAccount());

        if(wallet.getBalance() < withdraw.getAmount()) {
            throw new AppException(ErrorCode.INSUFFICIENT_BALANCE);
        }
        if(withdraw.getStatus().equals("APPROVED")) {
            throw new AppException(ErrorCode.WITHDRAW_ALREADY_APPROVED);
        }

        wallet.setBalance(wallet.getBalance()-withdraw.getAmount());
        walletRepo.save(wallet);

        Transactions transactions = Transactions.builder()
                .amount(-withdraw.getAmount())
                .date(Instant.now())
                .description("withdraw money to account number: " + withdraw.getAccountNumber())
                .type(TransactionsEnum.WITHDRAWAL)
                .status("COMPLETED")
                .wallet(wallet)
                .build();

        transactionsRepo.save(transactions);
        withdraw.setDescription("Withdraw approved successfully");
        withdraw.setStatus("APPROVED");
        withdraw.setStaff(staff);
        return withdrawMapper.toWithdrawDTO(withdrawRepo.save(withdraw));
    }

    @Override
    public WithdrawDTO rejectWithdraw(Long withdrawId, String staffId, RejectWithdrawRequest request) {
        Withdraw withdraw = withdrawRepo.findById(withdrawId)
                .orElseThrow(()->new AppException(ErrorCode.WITHDRAW_NOT_FOUND));
        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(()->new AppException(ErrorCode.STAFF_NOT_FOUND));

        Wallet wallet = walletRepo.findWalletByAccount(withdraw.getAccount());
        if(withdraw.getStatus().equals("APPROVED")) {
            throw new AppException(ErrorCode.WITHDRAW_ALREADY_APPROVED);
        }
        if(withdraw.getStatus().equals("REJECTED")) {
            throw new AppException(ErrorCode.WITHDRAW_ALREADY_REJECTED);
        }
        withdraw.setStatus("REJECTED");
        withdraw.setDescription(request.getDescription());
        withdraw.setStaff(staff);
        Transactions transactions = Transactions.builder()
                .amount(withdraw.getAmount())
                .date(Instant.now())
                .description(request.getDescription())
                .type(TransactionsEnum.WITHDRAWAL)
                .status("FAIL")
                .wallet(wallet)
                .build();
        transactionsRepo.save(transactions);
        return withdrawMapper.toWithdrawDTO(withdrawRepo.save(withdraw));
    }

    @Override
    public List<Withdraw> getAllWithdraw() {
        return withdrawRepo.findAll();
    }

    @Override
    public WithdrawDTO getWithdrawByAccountId(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Withdraw withdraw = withdrawRepo.findWithdrawByAccount(account)
                .orElseThrow(() -> new AppException(ErrorCode.WITHDRAW_NOT_FOUND));
        return withdrawMapper.toWithdrawDTO(withdraw);
    }
}
