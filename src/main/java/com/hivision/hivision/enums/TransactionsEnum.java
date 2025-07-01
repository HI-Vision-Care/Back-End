package com.hivision.hivision.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum TransactionsEnum {
    // nạp tiền, rút tiền, chuyển khoản, tiền mặt
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    CASH_PAYMENT,

}
