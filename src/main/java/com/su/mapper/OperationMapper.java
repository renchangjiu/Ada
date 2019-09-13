package com.su.mapper;

import com.su.pojo.Operation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperationMapper {

    void insert(Operation operation) throws Exception;

    List<Operation> list(@Param("index") int index, @Param("length") int length) throws Exception;

    int getTotalCount() throws Exception;

    Operation findById(Integer id) throws Exception;

}
