package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.enums.ConsultationStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.Role;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IAccountMapper;
import com.hivision.hivision.payload.request.AccountCreationRequest;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.request.UpdateAccountRequest;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.cservice.TokenService;
import com.hivision.hivision.service.iservice.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AccountService implements IAccountService {
    IAccountRepo iAccountRepository;
    IAccountMapper iAccountMapper;
    IDoctorRepo doctorRepo;
    IPatientRepo patientRepo;
    IStaffRepo staffRepo;
    IWalletRepo walletRepo;
    IChatBoxRepo chatBoxRepo;

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
//        var user = iAccountRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED_USERNAME));
        var user = iAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));

        if(user.getIsDeleted() != null && user.getIsDeleted()) {
            throw new AppException(ErrorCode.ACCOUNT_DELETED);
        }

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_PASSWORD);
        }

        var token = tokenService.generateToken(user);
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .role(user.getRole())
                .patient(patientRepo.findPatientByAccount(user))
                .doctor(doctorRepo.findDoctorByAccount(user))
//                .staff(staffRepo.findDoctorByAccount(user))
                .build();
    }

    @Override
    public AccountDTO register(RegisterRequest request) {
//        if (iAccountRepository.existsByUsername(request.getUsername())) {
//            throw new AppException(ErrorCode.USER_EXISTED);
//        }
        if(iAccountRepository.existsByEmail(request.getEmail())) {
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


        Patient patient = new Patient();
        patient.setAccount(account);
        patientRepo.save(patient);

        Wallet wallet = new Wallet();
        wallet.setAccount(account);
        wallet.setBalance(0.0); // Khởi tạo số dư ví là 0
        walletRepo.save(wallet);

        ChatBox chatBox = new ChatBox();
        chatBox.setAccPatient(account);
        chatBox.setStatus(ConsultationStatus.DEFAULT);
        chatBoxRepo.save(chatBox);

        // Tạo token cho người dùng mới
        var token = tokenService.generateToken(account);
        // Trả về AccountDTO với token
        return iAccountMapper.toAccountDTOWithToken(iAccountRepository.save(account), token);

//        return iAccountMapper.toAccountDTO(iAccountRepository.save(account));
    }

    @Override
    public AccountDTO createAccount(AccountCreationRequest request) {
        String role = request.getRole().toUpperCase();

        if(!Role.DOCTOR.name().equals(role) && !Role.STAFF.name().equals(role)) {
            throw new AppException(ErrorCode.ROLE_ERROR);
        }
        // Check if email already exists
        if (iAccountRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        Account account = iAccountMapper.toAccountCreation(request);
        account.setPassword(this.passwordEncoder.encode(request.getPassword()));
        account.setIsDeleted(false);
        iAccountRepository.save(account);

        if(Role.DOCTOR.name().equals(role)){
            account.setRole(Role.DOCTOR);
            Doctor doctor = new Doctor();
            doctor.setAccount(account);
            doctorRepo.save(doctor);
        }
        else{
            account.setRole(Role.STAFF);
            Staff staff = new Staff();
            staff.setAccount(account);
            staffRepo.save(staff);
        }
        return iAccountMapper.toAccountDTO(iAccountRepository.save(account));
    }


    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = iAccountRepository.findAll()
                .stream()
                .filter(d -> d.getIsDeleted() == null || !d.getIsDeleted())
                .toList();
        return accounts;
//        return iAccountRepository.findByRoleIsNot(Role.ADMIN);
//        return iAccountRepository.findAll();
    }

    @Override
    public Account findOrCreateByEmail(String email, String name, String avatar) {
        return iAccountRepository.findByEmail(email)
                .orElseGet(() -> {
                    Account newAccount = Account.builder()
                            .username(email.split("@")[0]) // Sử dụng email làm username,
                            .email(email)
                            .avatar(avatar)
                            .password(null)
//                            .role(Role.PATIENT)
                            .isDeleted(false)
                            .build();
                    return iAccountRepository.save(newAccount);
                });
    }

    @Override
    public void updateAccount(String accountId, UpdateAccountRequest request) {
        Account account = iAccountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        account.setPhone(request.getPhone());
        account.setAvatar(request.getAvatar());
        iAccountRepository.save(account);

    }

    @Override
    public void deleteAccount(String accountId) {
        Account account = iAccountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//        if (account.getRole() == Role.ADMIN) {
//            throw new AppException(ErrorCode.ADMIN_CANNOT_BE_DELETED);
//        }
        account.setRole(Role.BANNED);
        account.setIsDeleted(true);
        iAccountRepository.save(account);
    }


    public Account getCurrentAccount() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return iAccountRepository.findAccountById(account.getId());
    }
}
