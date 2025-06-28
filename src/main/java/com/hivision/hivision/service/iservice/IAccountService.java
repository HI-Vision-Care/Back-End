package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.pojo.Account;

import java.util.List;

public interface IAccountService {
    LoginResponse login(LoginRequest request);
    AccountDTO register(RegisterRequest request);
    List<Account> getAllAccounts();
}
