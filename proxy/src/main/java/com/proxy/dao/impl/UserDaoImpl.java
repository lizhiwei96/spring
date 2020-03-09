package com.proxy.dao.impl;

import com.proxy.dao.UserDao;

public class UserDaoImpl implements UserDao
{

    public void query() {
        System.out.println("假装查询用户的数据库");
    }

    public void query(String name, String address) {
        System.out.println(name+","+address);
    }

    @Override
    public String query(String name) {
        return name;
    }
}
