package com.peterjxl.dao;

import com.peterjxl.domain.QueryVo;
import com.peterjxl.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */


    List<User> findAll();

    /**
     * 保存新增的用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param user 查询的条件，有可能有用户名，有可能有性别，有可能有地址，有可能都有，也可能没有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo );
}
