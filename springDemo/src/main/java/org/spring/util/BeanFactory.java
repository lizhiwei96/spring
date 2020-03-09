package org.spring.util;

import com.spring.exception.BussinessException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeanFactory {

    private Map<String,Object> beans = new HashMap<String, Object>();

    public BeanFactory(String xml){
        parseXml(xml);
    }

    public void parseXml(String xml){
        File file = new File(this.getClass().getResource("/").getPath()+"//"+xml);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Attribute autowireAttribute = rootElement.attribute("default-autowire");
            boolean flag = false;
            if(null != autowireAttribute){
                flag = true;
            }
            for (Iterator<Element> itFirst = rootElement.elementIterator(); itFirst.hasNext();) {
                /**
                 * 1.实例化对象
                 */
                Element firstElement = itFirst.next();
                //可以直接调用attributeValue，等价于这两个方法
                String beanName = firstElement.attribute("id").getValue();
                String clazzName = firstElement.attribute("class").getValue();
                Class clazz = Class.forName(clazzName);
                Object object = null;
                /**
                 * 2.维护依赖关系
                 * 看这个对象有没有依赖关系(判断是否有属性property或constructor-arg)
                 * 如果有则注入
                 */
                for (Iterator<Element> itSecond = firstElement.elementIterator(); itSecond.hasNext();) {
                    Element secondElement = itSecond.next();
                    // <property name="userDao" ref="userDao"></property>
                    //<constructor-arg ref="userDao"></constructor-arg>
                    //获取标签名称 判断是否为set注入 获取name 和 ref 调用set方法
                    if(secondElement.getName().equals("property")){
                        object = clazz.newInstance();
                        String refValue = secondElement.attributeValue("ref");
                        Object injectObject = beans.get(refValue);
                        String nameValue = secondElement.attributeValue("name");
                        Field field = clazz.getDeclaredField(nameValue);
                        //私有的不允许访问 得先设置成能访问
                        field.setAccessible(true);
                        field.set(object,injectObject);
                    }else if (secondElement.getName().equals("constructor-arg")){
                        String refValue = secondElement.attributeValue("ref");
                        Object injectObject = beans.get(refValue);
                        //获取带参数的构造方法
                        Constructor<?> constructor = clazz.getDeclaredConstructor(injectObject.getClass().getInterfaces()[0]);
                        //通过构造方法创建对象
                        object = constructor.newInstance(injectObject);
                    }
                }

                //通过获取default-autowire值进行注入
                //手动专配大于自动装配
                if(null == object){//没有子属性的时候直接new出都对象
                    object = clazz.newInstance();
                    if(flag){
                        if(autowireAttribute.getValue().equals("byType")){
                            //判断是否有依赖
                            Field[] fields = clazz.getDeclaredFields();
                            for (Field field:fields) {
                                //得到属性的类型
                                Class<?> injectObjectClazz = field.getType();
                                //由于是byType,所以得遍历beans中所有对象的类型
                                int count = 0;
                                Object injectObject = null;
                                StringBuilder sb = new StringBuilder();
                                for (String key:beans.keySet()) {
                                    Class clazzTmp = beans.get(key).getClass().getInterfaces()[0];
                                    if(clazzTmp.getName().equals(injectObjectClazz.getName())){
                                        //记录找到一个 如果找到多个 抛出异常
                                        injectObject = beans.get(key);
                                        //存储找到bean的对象的名称
                                        sb.append(key+" ");
                                        count++;
                                    }
                                }
                                if(count > 1){
                                    //抛出找到多个的异常
                                    throw new BussinessException("通过类型找到多个bean对象:"+sb.toString());
                                }else if(count == 0){
                                    throw new BussinessException("通过类型未找到bean对象");
                                }else{
                                    //私有的不允许访问 得先设置成能访问
                                    field.setAccessible(true);
                                    field.set(object,injectObject);
                                }
                            }
                        }
                    }
                }
                beans.put(beanName,object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(beans);
    }

    public Object getBean(String beanName){
        return beans.get(beanName);
    }
}
