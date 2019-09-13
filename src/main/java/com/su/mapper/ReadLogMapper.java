package com.su.mapper;


import com.su.pojo.ReadLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReadLogMapper {

    List<ReadLog> list(@Param("index") Integer index, @Param("length") Integer length) throws Exception;

    Integer getTotalCount() throws Exception;

    void insert(ReadLog log) throws Exception;
}
