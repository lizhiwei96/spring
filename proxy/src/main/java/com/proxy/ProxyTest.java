package com.proxy;

import com.proxy.dao.UserDao;
import com.proxy.dao.impl.UserDaoImpl;
import com.proxy.proxyextend.UserDaoLogImpl;
import com.proxy.proxyinterface.UserDaoAuth;
import com.proxy.proxyinterface.UserDaoLog;
import com.proxy.util.ProxyUtil;

import java.lang.reflect.Proxy;


public class ProxyTest {

    public static void main(String[] args) {
       // UserDaoImpl userDaoImpl = new UserDaoAuthImpl();
        UserDao proxy = (UserDao) ProxyUtil.newInstance(new UserDaoImpl());
        System.out.println(proxy.query("李志伟"));
    }
}
