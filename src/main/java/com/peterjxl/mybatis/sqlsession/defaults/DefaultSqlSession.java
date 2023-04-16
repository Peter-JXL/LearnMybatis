package com.peterjxl.mybatis.sqlsession.defaults;

import com.peterjxl.cfg.Configuration;
import com.peterjxl.mybatis.sqlsession.SqlSession;
import com.peterjxl.mybatis.sqlsession.proxy.MapperProxy;
import com.peterjxl.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass
     * @return
     * @param <T>
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        Object o = Proxy.newProxyInstance(
                daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(), connection) {
                });
        return (T) o;
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
