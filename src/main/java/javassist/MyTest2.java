package javassist;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 动态编译代码类
 * @Author: yyl
 * @Date: 2019/3/6 16:51
 */
public class MyTest2 {
    public static void main(String[] args) throws Exception{
        //ClassPool是缓存CtClass对象的容器，所有的CtClass都放在ClassPool中，可以做成单例的
        ClassPool classPool = ClassPool.getDefault();
        //创建class
        CtClass ctClass = classPool.makeClass("javassist.MyBean2");
        //创建属性
        CtField username = CtField.make("private String username;",ctClass);
        CtField password = CtField.make("private String password;",ctClass);
        //添加属性进类
        ctClass.addField(username);
        ctClass.addField(password);
        //创建方法
        CtMethod sayUserName = CtMethod.make(String.format("public void sayUserName(){System.out.println(username);}"),ctClass);
        CtMethod sayPassword = CtMethod.make(String.format("public void sayPassword(){System.out.println(password);}"),ctClass);
        //添加方法进类
        ctClass.addMethod(sayPassword);
        ctClass.addMethod(sayUserName);
        //创建有参构造器
        CtConstructor constructor = new
                CtConstructor(new CtClass[]{classPool.get(String.class.getName()),classPool.get(String.class.getName())},ctClass);
        //设置构造器的修饰符
        constructor.setModifiers(Modifier.PUBLIC);
        //设置构造器的方法体  注意这里的获取构造方法中的参数是 $123...的形式 下标从1开始
        constructor.setBody("{this.username=$1;this.password=$2;}");
        //提那家构造器
        ctClass.addConstructor(constructor);
        Class clazz = ctClass.toClass();
        Constructor constructor1 = clazz.getConstructor(String.class,String.class);
        Object obj = constructor1.newInstance("yyl","123");
        Method method = clazz.getDeclaredMethod("sayUserName");
        Method method2 = clazz.getDeclaredMethod("sayPassword");
        method.invoke(obj);
        method2.invoke(obj);

    }

}
