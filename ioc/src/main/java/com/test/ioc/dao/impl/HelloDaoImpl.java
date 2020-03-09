package com.test.ioc.dao.impl;

import com.test.ioc.dao.HelloDao;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//自己指定bean的名称
@Repository("dao")
//@Scope("prototype")
//@Primary
//@Profile("dao")
public class HelloDaoImpl implements HelloDao {

    public HelloDaoImpl(){
        System.out.println("HelloDaoImpl");
    }

    public void test(){
        System.out.println("test HelloDao0");
    }
}
