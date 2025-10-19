package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.*;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.pojo.Account;

import java.util.List;

public interface IAccountService {
    LoginResponse login(LoginRequest request);
    AccountDTO register(RegisterRequest request);
    AccountDTO createAccount(AccountCreationRequest request);
    List<Account> getAllAccounts();
    Account findOrCreateByEmail(String email, String name, String avatar);
    void updateAccount(String accountId, UpdateAccountRequest request);
    void deleteAccount(String accountId);
    void forgotPassword(ForgotPasswordRequest request); // Gửi OTP
    void verifyOtp(VerifyOtpRequest request); // Xác thực OTP
    void resetPassword(ResetPasswordRequest request); // Đặt lại mật khẩu
}
