package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.WalletDTO;
import com.hivision.hivision.payload.request.WalletRequest;

public interface IWalletService {
    String createUrl(WalletRequest walletRequest, String accountId) throws Exception;

    void handleVnPayCallback(String transactionId, String responseCode, double vnpAmount);

    WalletDTO getWallet(String accountId);
    void deposit(String accountId, WalletRequest request);
}
