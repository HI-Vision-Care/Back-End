package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.WithdrawDTO;
import com.hivision.hivision.pojo.Withdraw;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IWithdrawMapper {
    WithdrawDTO toWithdrawDTO(Withdraw withdraw);
}
