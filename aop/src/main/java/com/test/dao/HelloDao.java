package com.test.dao;

import com.test.annotion.Aop;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class HelloDao implements  Dao{

    public void test(String name){
        System.out.println(name);
    }

    public void test(){
        System.out.println("test");
    }

    @Aop
    public void query(){
        System.out.println("query");
    }
}
