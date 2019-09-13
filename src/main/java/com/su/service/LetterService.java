package com.su.service;

import java.util.List;

import com.su.exception.MyException;
import com.su.mapper.LetterMapper;
import com.su.pojo.Letter;
import com.su.pojo.PageHelper;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LetterService {

    @Autowired
    private LetterMapper lm;


    public PageHelper list(Integer page, Integer pageSize) throws Exception {
        Integer totalCount = lm.getTotalCount();
        PageHelper<Letter> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<Letter> list = lm.list(pageHelper.getIndex(), pageSize);
        pageHelper.setObjects(list);
        return pageHelper;
    }

    public void delete(Integer letId) throws Exception {
        lm.delete(letId);
    }

    public void insert(Letter letter) throws Exception {
        // 转义
        letter.setName(StringUtil.htmlSpecialCharsEncode(letter.getName()));
        letter.setEmail(StringUtil.htmlSpecialCharsEncode(letter.getEmail()));
        letter.setMessage(StringUtil.htmlSpecialCharsEncode(letter.getMessage()));
        lm.insert(letter);

    }

    public Letter findById(Integer id) throws Exception {
        return lm.findById(id);
    }
}
