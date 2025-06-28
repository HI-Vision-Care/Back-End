package com.hivision.hivision.service;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.AccountCreationRequest;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.pojo.Account;

import java.util.List;

public interface IAccountService {
    LoginResponse login(LoginRequest request);
    AccountDTO register(RegisterRequest request);
    AccountDTO createAccount(AccountCreationRequest request);
    List<Account> getAllAccounts();
    Account findOrCreateByEmail(String email, String name, String avatar);
    void deleteAccount(String accountId);
}
