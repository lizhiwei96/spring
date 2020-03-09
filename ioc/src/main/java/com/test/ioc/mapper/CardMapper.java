package com.test.ioc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface CardMapper {

    @Select("select * from user")
    public List<Map<String,Object>> queryAllUser();
}
