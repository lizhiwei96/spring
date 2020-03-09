package com.test.ioc.dao.impl;

import com.test.ioc.dao.HelloDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("dao1")
//@Profile("dao1")
public class HelloDaoImpl1 implements HelloDao {

    HelloDaoImpl1(){
        System.out.println("HelloDaoImpl1");
    }

    public void test(){
        System.out.println("test HelloDao1");
    }
}
