package com.proxy.proxyextend;

import com.proxy.dao.impl.UserDaoImpl;

public class UserDaoLogImpl extends UserDaoImpl {

    @Override
    public void query() {
        System.out.println("----extends log----");
        super.query();
    }
}
