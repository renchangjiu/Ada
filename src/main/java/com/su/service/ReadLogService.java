package com.su.service;

import java.util.List;

import com.su.exception.MyException;
import com.su.pojo.PageHelper;
import com.su.pojo.ReadLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.su.mapper.ReadLogMapper;


@Service
public class ReadLogService {

    @Autowired
    private ReadLogMapper rm;


    public void insert(ReadLog log) throws Exception {
        rm.insert(log);
    }

    public PageHelper list(Integer page, Integer pageSize) throws Exception {
        Integer totalCount = rm.getTotalCount();
        PageHelper<ReadLog> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<ReadLog> list = rm.list(pageHelper.getIndex(), pageSize);
        pageHelper.setObjects(list);
        return pageHelper;
    }

}
