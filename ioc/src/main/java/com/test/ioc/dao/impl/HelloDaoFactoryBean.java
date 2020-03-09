package com.test.ioc.dao.impl;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 如果你的类实现了FactoryBean
 * 那么spring容器当中存在两个对象
 * 一个是getObject()返回的对象
 * 一个是当前的对象
 *
 * getObject得到的对象存的是当前类指定的名字
 * 当前对象是“&”+当前类的名字
 */
@Component
public class HelloDaoFactoryBean implements FactoryBean {

    public void test(){
        System.out.println("HelloDaoFactoryBean");
    }

    @Override
    public Object getObject() throws Exception {
        return new TempHelloDaoFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TempHelloDaoFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
