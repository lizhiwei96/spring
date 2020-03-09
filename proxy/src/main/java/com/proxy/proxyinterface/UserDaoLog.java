package com.proxy.proxyinterface;

import com.proxy.dao.UserDao;

public class UserDaoLog implements UserDao {

    private UserDao userDao;

    public UserDaoLog(UserDao userDao){
        this.userDao = userDao;
    }

    public void query() {
        System.out.println("----interface log----");
        userDao.query();
    }

    public void query(String name, String address) {

    }

    @Override
    public String query(String name) {
        return null;
    }
}
