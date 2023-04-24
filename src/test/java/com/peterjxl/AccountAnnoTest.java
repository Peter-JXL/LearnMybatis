package com.peterjxl;

import com.peterjxl.dao.IAccountDao;
import com.peterjxl.dao.IUserDao;
import com.peterjxl.domain.Account;
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
import java.util.List;

public class AccountAnnoTest {

    private InputStream in;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao iAccountDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        iAccountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<Account> accounts = iAccountDao.findAll();
        for(Account account : accounts){
            System.out.print(account);
            System.out.println(account.getUser());
        }
    }

}
