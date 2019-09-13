package com.su.mapper;

import java.util.List;

import com.su.pojo.SignInLog;
import org.apache.ibatis.annotations.Param;

public interface SignInLogMapper {

    List<SignInLog> list(@Param("index") Integer index, @Param("length") Integer length) throws Exception;

    int getTotalCount() throws Exception;

    void insert(SignInLog loginLog) throws Exception;
}
