package com.proxy.util;


import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtil {

    private ProxyUtil(){}

    /**
     * content ---> String
     * .java  可以new创建 可以通过io创建
     * .class
     * new 出这个对象
     * @return
     */
    public static Object newInstance(Object target){
        //生成一个代理类的内容 并new出对象
        Object returnObject = null;
        //换行和制表
        String line = "\n";
        String tab = "\t";
        //获取这个对象的接口
        Class targetInf = target.getClass().getInterfaces()[0];
        String packageInf = "package com.lzw.proxy;"+line+line;
        String importInf = "import "+targetInf.getName()+";"+line+line;
        String classInf = "public class TestProxy implements "+targetInf.getSimpleName()+"{"+line+line;
        String firstInf = tab+"private "+targetInf.getSimpleName()+" userDao;"+line+line;
        String secondInf = tab+"public TestProxy("+targetInf.getSimpleName()+" userDao){"
                +line+tab+tab+"this.userDao = userDao;"+line+tab+"}"+line+line;
        String methodInfs = "";
        //获取接口中要实现的方法
        Method[] methods = targetInf.getMethods();
        for(Method method : methods){
            String methodInf = "public "+method.getReturnType().getSimpleName()+" "+method.getName()+"(";
            Class[] args = method.getParameterTypes();
            int flag = 0;
            if(args.length>0){
                for(Class arg : args){
                    methodInf = methodInf+arg.getSimpleName()+" p"+flag+",";
                    flag++;
                }
                methodInf = methodInf.substring(0,methodInf.lastIndexOf(","));
            }
            methodInf = methodInf+"){"+line;
            methodInf = tab+methodInf+tab+tab+"System.out.println(\"----interface log----\");"+line;
            String argsTmp = "";
            if(args.length>0){
                for (int i = 0; i < flag; i++) {
                    argsTmp += "p"+i+",";
                }
                argsTmp = argsTmp.substring(0,argsTmp.lastIndexOf(","));
            }
            if(method.getReturnType().getSimpleName().equals("void")){
                methodInf += tab+tab+"userDao."+method.getName()+"("+argsTmp+");"+line+tab+"}"+line+line;
            }else{
                methodInf += tab+tab+"return userDao."+method.getName()+"("+argsTmp+");"+line+tab+"}"+line+line;
            }
            methodInfs +=methodInf;
        }
        String content = packageInf+importInf+classInf+firstInf+secondInf+methodInfs+"}";
        //创建了文件
        File file = new File("D:\\com\\lzw\\proxy\\TestProxy.java");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            fw.close();

            //编译java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,null,null);
            Iterable units = fileMgr.getJavaFileObjects(file);
            JavaCompiler.CompilationTask t = compiler.getTask(null,fileMgr,null,null,null,units);//编译任务
            t.call();
            fileMgr.close();
            //加载class文件
            URL[] urls = new URL[] {new URL("file:D:\\\\")};
            URLClassLoader ul = new URLClassLoader(urls);
            Class c = ul.loadClass("com.lzw.proxy.TestProxy");
            Constructor constructor = c.getConstructor(targetInf);
            returnObject = constructor.newInstance(target);
        }catch (Exception e){
            e.printStackTrace();
        }


//        System.out.println(content);
        return returnObject;
    }
}
