package com.proxy.proxyextend;

import com.proxy.dao.impl.UserDaoImpl;

public class UserDaoAuthImpl extends UserDaoImpl {

    @Override
    public void query() {
        System.out.println("----extends auth----");
        super.query();
    }
}
