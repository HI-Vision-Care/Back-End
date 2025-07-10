package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.AccountCreationRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.pojo.Account;
import org.mapstruct.Mapper;

@Mapper
public interface IAccountMapper {
    Account toAccount(RegisterRequest accountCreationRequest);
    Account toAccountCreation(AccountCreationRequest accountCreationRequest);
    AccountDTO toAccountDTO(Account account);
    AccountDTO toAccountDTOWithToken(Account account, String token);
}
