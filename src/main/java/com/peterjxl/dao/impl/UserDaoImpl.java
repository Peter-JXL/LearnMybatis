package com.peterjxl.dao.impl;

import com.peterjxl.dao.IUserDao;
import com.peterjxl.domain.QueryVo;
import com.peterjxl.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.peterjxl.dao.IUserDao.findAll");
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.peterjxl.dao.IUserDao.saveUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("com.peterjxl.dao.IUserDao.updateUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        session.delete("com.peterjxl.dao.IUserDao.deleteUser", userId);
        session.commit();
        session.close();
    }

    @Override
    public User findById(Integer userId) {
        SqlSession session = factory.openSession();
        User u = session.selectOne("com.peterjxl.dao.IUserDao.findById", userId);
        session.close();
        return u;
    }

    @Override
    public List<User> findByName(String name) {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.peterjxl.dao.IUserDao.findByName", name);
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        SqlSession session = factory.openSession();
        int n = session.selectOne("com.peterjxl.dao.IUserDao.findTotal");
        session.close();
        return n;
    }

    @Override
    public List<User> findUserByVo(QueryVo vo) {
        return null;
    }
}
