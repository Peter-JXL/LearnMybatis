package com.peterjxl.test;

import com.peterjxl.dao.IUserDao;
import com.peterjxl.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.close();
        in.close();
    }

    @Test
    public void helloMybatis() throws Exception{
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testFirstLevelCache(){
        User user = userDao.findById(41);
        System.out.println("user: " + user);


//        session.close();
//        session = factory.openSession();
//        userDao = session.getMapper(IUserDao.class);
        session.clearCache();

        User user2 = userDao.findById(41);
        System.out.println("user2: " + user2);

        System.out.println("user2 == user ? : " + (user2 == user));
    }


    @Test
    public void testClearCache(){
        // 1. 根据 id 查询用户信息
        User user = userDao.findById(41);
        System.out.println("user: " + user);

        // 2. 更新用户信息
        user.setUsername("update user clear cache");
        user.setAddress("克莱登大学");
        userDao.updateUser(user);

        // 3. 再次查询id 为41的用户
        User user2 = userDao.findById(41);
        System.out.println("user2: " + user2);

        System.out.println("user2 == user ? : " + (user2 == user));
    }
}
