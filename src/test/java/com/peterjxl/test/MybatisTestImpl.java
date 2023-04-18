package com.peterjxl.test;

import com.peterjxl.dao.IUserDao;
import com.peterjxl.dao.impl.UserDaoImpl;
import com.peterjxl.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTestImpl {

    private InputStream in;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destory() throws IOException {
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUserName("dao impl user");
        user.setUserAddress("广州市番禺区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        System.out.println("保存操作之前：: " + user);
        userDao.saveUser(user);
        System.out.println("保存操作之前：: " + user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(41); //张三的数据
        user.setUserName("mybatis update impl user");
        user.setUserAddress("广州市番禺区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDelete(){
        userDao.deleteUser(55);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(41);
        System.out.println(user);
    }


    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("mybatis%");
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println("total: " + total);
    }


}
