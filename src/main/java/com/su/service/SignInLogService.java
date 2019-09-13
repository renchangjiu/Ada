package com.su.service;


import com.su.exception.MyException;
import com.su.mapper.SignInLogMapper;
import com.su.pojo.PageHelper;
import com.su.pojo.SignInLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SignInLogService {

    @Autowired
    private SignInLogMapper sm;

    public PageHelper list(Integer page, Integer pageSize) throws Exception {
        Integer totalCount = sm.getTotalCount();
        PageHelper<SignInLog> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<SignInLog> list = sm.list(pageHelper.getIndex(), pageSize);
        pageHelper.setObjects(list);
        return pageHelper;
    }

    public void insert(SignInLog log) throws Exception {
        sm.insert(log);
    }

}
