package com.test.ioc.customPostProcessor;

import com.test.ioc.customFactoryBean.MyFactoryBean;
import com.test.ioc.mapper.CardMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanRegistarPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //得到bd
        //这里应该是扫描MapperScan中value值下的所有接口
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CardMapper.class);
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
        //beanDefinition.getBeanClassName() = com.test.ioc.mapper.CardMapper
        //使用beanDefinition.getBeanClassName()一定要setBeanClass在前，因为这个方法需要使用beanClass
        //beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.test.ioc.mapper.CardMapper");
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
        beanDefinition.setBeanClass(MyFactoryBean.class);
        registry.registerBeanDefinition("cardMapper",beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory后置处理器");
    }
}
