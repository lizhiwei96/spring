package com.test.ioc.service;

import com.test.ioc.dao.HelloDao;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class HelloService{


    @Autowired
    @Qualifier("dao")//指定是哪一个bean对象
    private HelloDao helloDao;

    private String str;

    private ApplicationContext applicationContext;

    public HelloService(){
        System.out.println("HelloService的构造方法");
    }

//    @PostConstruct
//    public void afterPropertiesSet(){
//        System.out.println("init初始化方法");
//    }

    public void service(){
//        System.out.println(str);
        helloDao.test();
//        System.out.println("helloDao:"+getDao().hashCode());
//        System.out.println("service:"+this.hashCode());
    }
    //默认通过返回值的类型来获取  如果有value值则为通过bean的名称获取到容器中的bean
//    @Lookup("dao1")
//    public abstract HelloDao getDao();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//构造方法注入
//    HelloService(HelloDao dao){
//        this.dao = dao;
//    }
//setter注入
    public void setHelloDao(HelloDao dao) {
        this.helloDao = dao;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

}
