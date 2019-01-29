package cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: yyl
 * @Date: 2019/1/16 14:24
 */
public class MyTest1 {
    /**
     * spring-cglib的使用
     * 通用写法
     * @param args
     */
    public static void main(String[] args){
        DaoProxy daoProxy = new DaoProxy();
        //创建一个增强器
        Enhancer enhancer = new Enhancer();
        //设置要代理的父类
        enhancer.setSuperclass(Dao.class);
        //设置动态代理的回调方法
        enhancer.setCallback(daoProxy);
        //构造函数不拦截方法，默认为true
        enhancer.setInterceptDuringConstruction(false);
        Dao dao = (Dao)enhancer.create();
        //这里可以看到方法内部的调用也会触发回调方法
        //这里的方法内部调用会触发动态代理是因为被代理对象是由cglib生成的，在拦截方法中
        //明确执行了methodProxy.invokeSuper(o,objects); 也就是被代理对象的方法，此时的o是由cglib生成的代理对象
        //所以方法内部调用时的this对象仍然是代理对象，所以基于cglib的动态代理的方法内部调用能触发
        //基于cglib的AOP不能实现原因 查看MyTest4
        System.out.println(dao.select());
        System.out.println(dao.update());
    }

}
