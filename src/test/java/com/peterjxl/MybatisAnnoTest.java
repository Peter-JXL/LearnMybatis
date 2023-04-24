package com.peterjxl;

import com.peterjxl.dao.IUserDao;
import com.peterjxl.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisAnnoTest {

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
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.print(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void testDelete(){
        userDao.deleteUser(56);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(41);
        System.out.println(user);

        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println( "user == user2 ? " + (user == user2));
    }

    @Test
    public void testFindByName(){
//        List<User> users = userDao.findUserByName("mybatis%");
        List<User> users = userDao.findUserByName("mybatis");
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int total = userDao.findTotalUser();
        System.out.println("total: " + total);
    }

}
