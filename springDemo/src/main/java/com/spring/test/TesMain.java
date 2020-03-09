package com.spring.test;

import com.spring.service.UserService;
import org.spring.util.BeanFactory;

public class TesMain {

    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory("spring.xml");
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.find();
    }
}
