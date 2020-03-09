package com.proxy.proxyinterface;

import com.proxy.dao.UserDao;

public class UserDaoAuth implements UserDao {

    private UserDao userDao;

    public UserDaoAuth(UserDao userDao){
        this.userDao = userDao;
    }

    public void query() {
        System.out.println("----interface auth---");
        userDao.query();
    }

    public void query(String name, String address) {

    }

    @Override
    public String query(String name) {
        return null;
    }
}
