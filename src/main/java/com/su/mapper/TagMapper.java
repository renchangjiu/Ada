package com.su.mapper;

import java.util.List;

import com.su.pojo.*;

public interface TagMapper {

    String findNameById(Integer id) throws Exception;

    List<Tag> list() throws Exception;

    void insert(Tag tag) throws Exception;

    void delete(Integer id) throws Exception;

    Tag findById(Integer id) throws Exception;

}
