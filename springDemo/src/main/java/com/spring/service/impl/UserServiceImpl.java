package com.spring.service.impl;

import com.spring.dao.UserDao;
import com.spring.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao;

//    public UserServiceImpl(UserDao userDao){
//        System.out.println("构造方法注入");
//        this.userDao = userDao;
//    }

    public void find() {
        System.out.println("service-----find");
        userDao.query();
    }

    public void setUserDao(UserDao userDao){
        System.out.println("setter注入");
        this.userDao = userDao;
    }
}
