package com.hivision.hivision.service;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.response.LoginResponse;

public interface IAccountService {
    LoginResponse login(LoginRequest request);
    AccountDTO register(RegisterRequest request);
}
