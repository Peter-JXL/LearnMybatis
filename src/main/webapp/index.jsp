<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.peterjxl.dao.IUserDao" %>
<%@ page import="com.peterjxl.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Hello JNDI</title>
    </head>
    <body>
        <%
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession sqlSession = factory.openSession();
            IUserDao userDao = sqlSession.getMapper(IUserDao.class);
            List<User> users = userDao.findAll();
            for(User user : users){
                System.out.println(user);
            }
            sqlSession.clearCache();
            in.close();
        %>
    </body>
</html>
