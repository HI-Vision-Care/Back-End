package com.hivision.hivision.service;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.Role;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IAccountMapper;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.repository.IAccountRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccountService implements IAccountService{
    IAccountRepo iAccountRepository;
    IAccountMapper iAccountMapper;

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        var user = iAccountRepository.findByUsername(request.getUsername())

                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED_USERNAME));

//        var user = iAccountRepository.findByEmail(request.getUsername())
//                .orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_PASSWORD);
        }

        var token = tokenService.generateToken(user);
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public AccountDTO register(RegisterRequest request) {
        if (iAccountRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        else if(iAccountRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }else if(iAccountRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONE_EXISTED);
        }

        Account account = iAccountMapper.toAccount(request);
        account.setPassword(this.passwordEncoder.encode(request.getPassword()));
        account.setRole(Role.PATIENT);
        account.setIsDeleted(false); // mặc định là false
        account = iAccountRepository.save(account);


        //gui email ve cho nguoi dung
//        EmailDetail emailDetail = new EmailDetail();
//        emailDetail.setReceiver(account);
//        emailDetail.setSubject("Welcome to BidKoi");
//        emailDetail.setLink("http://localhost:5173/login");
//        emailService.sendEmail(emailDetail);







        return iAccountMapper.toAccountDTO(iAccountRepository.save(account));
    }

    @Override
    public List<Account> getAllAccounts() {
        return iAccountRepository.findAll();
    }
}
