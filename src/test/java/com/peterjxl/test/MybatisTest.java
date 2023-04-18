package com.peterjxl.test;

import com.peterjxl.dao.IUserDao;
import com.peterjxl.domain.QueryVo;
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

public class MybatisTest {

    private InputStream in;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        // 1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2. 创建SqlSessionFactory工厂
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

        // 3. 使用工厂生成SqlSession对象
        session = factory.openSession();

        // 4. 使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        // 6. 释放资源
        session.close();
        in.close();
    }


    @Test
    public void helloMybatis() throws Exception{
        // 5. 使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUsername("mybatis saveuser");
        user.setAddress("广州市番禺区");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存操作之前：: " + user);
        userDao.saveUser(user);
        System.out.println("保存操作之前：: " + user);
        session.commit();

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(41); //张三的数据
        user.setUsername("mybatis update user");
        user.setAddress("广州市番禺区");
        user.setSex("男");
        user.setBirthday(new Date());

        // 5. 使用代理对象执行方法
        userDao.updateUser(user);
        session.commit();
    }

    @Test
    public void testDelete(){
        // 5. 使用代理对象执行方法
        userDao.deleteUser(50);
        session.commit();
    }

    @Test
    public void testFindOne(){
        // 5. 使用代理对象执行方法
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

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println("total: " + total);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){

        User user = new User();
        user.setUsername("mybatis%");

        QueryVo vo = new QueryVo();
        vo.setUser(user);

        List<User> users = userDao.findUserByVo(vo);
        for(User u : users){
            System.out.println(u);
        }
    }
}
