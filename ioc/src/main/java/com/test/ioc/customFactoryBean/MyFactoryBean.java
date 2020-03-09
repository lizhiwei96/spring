package com.test.ioc.customFactoryBean;

import com.test.ioc.IocApplication;
import com.test.ioc.mapper.CardMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Component
public class MyFactoryBean implements FactoryBean, InvocationHandler {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private Class[] clazzs;

    public MyFactoryBean(Class[] clazzs){
        this.clazzs = clazzs;
    }

    @Override
    public Object getObject() throws Exception {
        CardMapper cardMapper = (CardMapper) Proxy.newProxyInstance(this.getClass().getClassLoader(),this.clazzs,this);
        return cardMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return CardMapper.class;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理cardMapper类");
        //执行select语句
        Method method1 = proxy.getClass().getInterfaces()[0].getMethod(method.getName());
        //select注解原理
        Select annotation = method1.getDeclaredAnnotation(Select.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PreparedStatement preparedStatement = sqlSession.getConnection().prepareStatement(annotation.value()[0]);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("username"));
        }
        sqlSession.close();
        return null;
    }
}
