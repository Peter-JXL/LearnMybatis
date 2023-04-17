package com.peterjxl.dao;

import com.peterjxl.domain.User;
import com.peterjxl.mybatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */

    @Select("select * from user")
    List<User> findAll();
}
