package com.test.ioc.service;

import com.test.ioc.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CardService {

    @Resource
    public CardMapper cardMapper;

    public void getAllUser(){
        System.out.println(cardMapper.queryAllUser());
    }
}
