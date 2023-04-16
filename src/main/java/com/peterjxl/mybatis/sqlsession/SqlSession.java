package com.peterjxl.mybatis.sqlsession;

import java.sql.SQLException;

/**
 * 自定义Mybatis中和数据库交互的核心类，可以创建dao接口的代理对象
 */
public interface SqlSession {
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 是否资源
     */
    void close() throws SQLException;
}
