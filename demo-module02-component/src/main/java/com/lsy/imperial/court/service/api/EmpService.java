package com.lsy.imperial.court.service.api;

import com.lsy.imperial.court.entity.Emp;

public interface EmpService {
    Emp getEmpByLogin(String loginAccount, String loginPassword);
}
