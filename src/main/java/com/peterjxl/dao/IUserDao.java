package com.peterjxl.dao;

import com.peterjxl.domain.User;
import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);


    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);
}
