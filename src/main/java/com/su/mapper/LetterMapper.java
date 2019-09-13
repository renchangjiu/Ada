package com.su.mapper;

import com.su.pojo.Letter;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface LetterMapper {

    List<Letter> list(@Param("index") int index, @Param("length") int length) throws Exception;

    int getTotalCount() throws Exception;

    void delete(Integer letId) throws Exception;

    void insert(Letter letter) throws Exception;

    Letter findById(Integer id) throws Exception;
}
