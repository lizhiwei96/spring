package com.test;

import com.test.config.AppConfig;
import com.test.dao.Dao;
import com.test.dao.HelloDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class AopTest {

    public static void main(String[] args) {
        //测试自定义注解
//        HelloEntity helloEntity = new HelloEntity();
//        helloEntity.setId("1");
//        helloEntity.setName("lzw");
//        String sql = SqlUtil.buildQuerySqlForEntity(helloEntity);
//        System.out.println(sql);
        //AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AppConfig.class);
        Class<?>[] interfaces = new Class[]{Dao.class};
        byte[] bytes = ProxyGenerator.generateProxyClass("lzw",interfaces);
        File file = new File("D:\\Dao.class");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Dao dao = (Dao) ioc.getBean("helloDao");
//        Dao dao1 = (Dao) ioc.getBean("helloDao");
//        System.out.println(dao instanceof HelloDao);
//        System.out.println(dao instanceof Proxy);
//        dao.test("名字");
//        System.out.println("------------------");
//        dao.test();
//        System.out.println("------------------");
//        dao.query();
//        System.out.println("------------------");
//        Dao helloDao1 = (Dao) ioc.getBean("helloDao1");
//        helloDao1.query();
//        dao1.query();
    }
}
