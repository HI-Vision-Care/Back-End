package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.WithdrawDTO;
import com.hivision.hivision.payload.request.RejectWithdrawRequest;
import com.hivision.hivision.payload.request.WithdrawRequest;
import com.hivision.hivision.pojo.Withdraw;

import java.util.List;

public interface IWithdrawService {
    WithdrawDTO createWithdraw(WithdrawRequest request, String accountId);
    WithdrawDTO approveWithdraw(Long withdrawId, String staffId);
    WithdrawDTO rejectWithdraw(Long withdrawId, String staffId, RejectWithdrawRequest request);
    List<Withdraw> getAllWithdraw();
    WithdrawDTO getWithdrawByAccountId(String accountId);
}
