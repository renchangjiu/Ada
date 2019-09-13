package com.su.mapper;

import com.su.pojo.Admin;

public interface AuthMapper {

    Admin findAdminByName(String name) throws Exception;

    Admin findAdminByNameAndPassword(Admin formAdmin) throws Exception;


}
