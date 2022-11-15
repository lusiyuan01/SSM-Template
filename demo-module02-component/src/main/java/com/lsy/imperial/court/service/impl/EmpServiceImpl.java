package com.lsy.imperial.court.service.impl;

import com.lsy.imperial.court.entity.Emp;
import com.lsy.imperial.court.entity.EmpExample;
import com.lsy.imperial.court.mapper.EmpMapper;
import com.lsy.imperial.court.service.api.EmpService;
import com.lsy.imperial.court.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp getEmpByLogin(String loginAccount, String loginPassword) {

        // 对密码进行 MD5 加密
        String encodeLoginPassword = MD5Util.encode(loginPassword);

        EmpExample empExample = new EmpExample();
        EmpExample.Criteria criteria = empExample.createCriteria();
        criteria.andLoginAccountEqualTo(loginAccount).andLoginPasswordEqualTo(encodeLoginPassword);

        List<Emp> empList = empMapper.selectByExample(empExample);

        if ( empList != null && empList.size() > 0) {
            return empList.get(0);
        }

        return null;
    }
}
