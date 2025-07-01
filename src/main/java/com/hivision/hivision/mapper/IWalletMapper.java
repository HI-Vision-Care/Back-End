package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.WalletDTO;
import com.hivision.hivision.payload.request.WalletRequest;
import com.hivision.hivision.pojo.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IWalletMapper {
     Wallet toWallet(WalletRequest request);
     WalletDTO toWalletDTO(Wallet wallet);
}
