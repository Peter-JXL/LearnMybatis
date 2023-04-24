package com.peterjxl.dao;

import com.peterjxl.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface IUserDao {
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id=true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(column = "id", property = "accounts",
                    many = @Many(select = "com.peterjxl.dao.IAccountDao.findAccountByUid", fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    @Insert("insert into user(username, address, sex, birthday) values (#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    @Update("update user set username=#{username}, address=#{address}, sex=#{sex}, birthday=#{birthday}  where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer userId);

    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    User findById(Integer id);

    //@Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    List<User> findUserByName(String username);

    @Select("select count(*) from user")
    int findTotalUser();
}
