package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Staff;
import com.hivision.hivision.repository.IAccountRepo;
import com.hivision.hivision.repository.IStaffRepo;
import com.hivision.hivision.service.iservice.IStaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class StaffService implements IStaffService {

    IAccountRepo accountRepo;
    IStaffRepo staffRepo;

    @Override
    public Staff getStaffByAccountID(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return staffRepo.findStaffByAccount(account);
    }
}
