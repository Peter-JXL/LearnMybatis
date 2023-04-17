package com.peterjxl.mybatis.sqlsession;

import com.peterjxl.mybatis.cfg.Configuration;
import com.peterjxl.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.peterjxl.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
