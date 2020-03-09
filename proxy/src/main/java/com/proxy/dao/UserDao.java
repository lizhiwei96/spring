package com.proxy.dao;

public interface UserDao {

    void query();

    void query(String name,String address);

    String query(String name);
}
