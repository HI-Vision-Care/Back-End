package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.TransactionsDTO;
import com.hivision.hivision.pojo.Transactions;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ITransactionsMapper {

    TransactionsDTO toTransactionsDTO(Transactions transactions);
    List<TransactionsDTO> toTransactionsDTO(List<Transactions> transactions);
}
