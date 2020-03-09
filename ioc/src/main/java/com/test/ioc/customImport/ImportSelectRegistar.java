package com.test.ioc.customImport;

import com.test.ioc.customFactoryBean.MyFactoryBean;
import com.test.ioc.mapper.CardMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


public class ImportSelectRegistar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //得到bd
        //这里应该是扫描MapperScan中value值下的所有接口
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CardMapper.class);
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
        beanDefinition.setBeanClass(MyFactoryBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new Class[]{CardMapper.class});
        registry.registerBeanDefinition("cardMapper",beanDefinition);
    }
}
