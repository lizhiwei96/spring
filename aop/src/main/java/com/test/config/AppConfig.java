package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=false)//为false则为jdk动态代理(基于实现)  为true则为cglib代理(基于继承)
@ComponentScan("com")
public class AppConfig {


}
