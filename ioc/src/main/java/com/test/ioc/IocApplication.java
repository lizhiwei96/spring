package com.test.ioc;

import com.test.ioc.config.SpringConfig;
import com.test.ioc.dao.HelloDao;
import com.test.ioc.dao.impl.HelloDaoFactoryBean;
import com.test.ioc.dao.impl.TempHelloDaoFactoryBean;
import com.test.ioc.service.CardService;
import com.test.ioc.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring.xml");
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
//        HelloService helloService = (HelloService) ioc.getBean("helloService");
//        helloService.service();
//        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
//        ioc.getEnvironment().setActiveProfiles("dao");
//        ioc.register(SpringConfig.class);
        //刷新容器
//        ioc.refresh();
//        ioc.getBean(HelloDao.class);
            CardService cardService =  ioc.getBean(CardService.class);
            cardService.getAllUser();
//        TempHelloDaoFactoryBean helloDaoFactoryBean = (TempHelloDaoFactoryBean) ioc.getBean("&helloDaoFactoryBean");
//        helloDaoFactoryBean.test();
//        HelloDaoFactoryBean helloDaoFactoryBean = (HelloDaoFactoryBean) ioc.getBean("&helloDaoFactoryBean");
//        helloDaoFactoryBean.test();
    }


}
