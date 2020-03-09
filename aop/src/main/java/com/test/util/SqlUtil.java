package com.test.util;

import com.test.annotion.Entity;

import java.lang.annotation.Annotation;

public class SqlUtil {

    private SqlUtil(){};

    /**
     * 通过对象生成一条sql语句
     * @param obj
     * @return
     */
    public static String buildQuerySqlForEntity(Object obj){
        Class clazz = obj.getClass();
        String value = "";
        //判断是否添加了注解
        if(clazz.isAnnotationPresent(Entity.class)){
            //得到注解
            Entity entity = (Entity) clazz.getAnnotation(Entity.class);
            value = entity.value();
        }
        String sql = "select * from " + value;
        return sql;
    }
}
