package com.test.config;


import com.test.dao.Dao;
import com.test.dao.HelloDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Aspect("perthis(pointCutThis())")
@Scope("prototype")
public class AopAspect {

    //可以理解为实现的dao这个接口，然后复制了一份HelloDao中的实现方法。
    @DeclareParents(value="com.test.dao.*", defaultImpl= HelloDao.class)
    public static Dao dao;

    /**
     * 切点
     * 位置 逻辑
     */
    @Pointcut("execution(* com.test.dao.*.*(java.lang.String,..))")
    public void pointCutExecution() {

    }

    @Pointcut("within(com.test.dao.*)")
    public void pointCutWithin() {
    }

    @Pointcut("args(java.lang.String,java.lang.Integer)")
    public void pointCutArgs() {

    }

    @Pointcut("@annotation(com.test.annotion.Aop)")
    public void pointCutAnnotation() {

    }

//        @Pointcut("this(com.test.dao.HelloDao)") //this是代理对象 cglib代理可以 jdk代理不行
    @Pointcut("this(com.test.dao.Dao)")
    public void pointCutThis() {

    }

    @Pointcut("target(com.test.dao.HelloDao)")
    public void pointCutTarget() {

    }

    /**
     * joinPoint 连接点的一些东西，例如签名，参数，返回值，目标对象，代理对象
     */
    @Before("pointCutWithin()")
    public void before(JoinPoint joinPoint) {
        System.out.println("前置通知");
//        System.out.println(joinPoint.getThis());
    }

    @After("pointCutWithin()")
    public void after() {
        System.out.println("后置通知");
    }

    @Around("pointCutWithin()")
    public void around(ProceedingJoinPoint pjp) {
        System.out.println(this.hashCode());
        System.out.println("aaa");
        try {
            Object[] args = pjp.getArgs();
            if(null != args && args.length>0){
                for (int i = 0; i < args.length; i++) {
                    args[i] += ":lizhiwei";
                }
            }
            pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("bbb");
    }
}
