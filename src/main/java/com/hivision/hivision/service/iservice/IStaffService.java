package com.hivision.hivision.service.iservice;

import com.hivision.hivision.pojo.Staff;

public interface IStaffService {
    Staff getStaffByAccountID(String accountId);
}
