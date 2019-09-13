package com.su.service;

import com.su.exception.MyException;
import com.su.mapper.LetterMapper;
import com.su.mapper.OperationMapper;
import com.su.pojo.Letter;
import com.su.pojo.Operation;
import com.su.pojo.PageHelper;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OperationService {

    @Autowired
    private OperationMapper om;


    public PageHelper list(Integer page, Integer pageSize) throws Exception {
        Integer totalCount = om.getTotalCount();
        PageHelper<Operation> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<Operation> list = om.list(pageHelper.getIndex(), pageSize);
        pageHelper.setObjects(list);
        return pageHelper;
    }


    public void insert(Operation operation) throws Exception {
        om.insert(operation);
    }

    public Operation show(Integer id) throws Exception {
        return om.findById(id);
    }
}
