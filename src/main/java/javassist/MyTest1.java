package javassist;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * javassist生成动态代理的方式有两种，一种是使用代理工厂创建，和jdk或者cglib类似
 * 另一种则是使用动态代码创建
 * @Author: yyl
 * @Date: 2019/3/6 15:59
 */
public class MyTest1 {
    /**
     * 使用代理工厂创建
     */
    @Test
    public void test1() throws Exception{
        final MyBean myBean = new MyBean();
        //创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        //设置被代理的类
        proxyFactory.setSuperclass(MyBean.class);
        //创建代理类的class
        Class<MyBean> proxyClass = proxyFactory.createClass();
        //创建对象
        MyBean proxy = proxyClass.newInstance();
        //定义一个拦截器，类似 jdk中的InvocationHandler接口
        ((ProxyObject)proxy).setHandler(new MethodHandler() {
            /**
             *
             * @param self 代理类实例
             * @param thisMethod 这个应该就是要调用的真正方法，也就是被代理的方法
             * @param proceed 生成的代理类对方法的代理引用
             * @param args 参数
             * @return
             * @throws Throwable
             */
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                System.out.println("before-----------");
                Object obj = thisMethod.invoke(myBean,args);
                System.out.println("after------------");
                return obj;
            }
        });
        proxy.say("hahaha");

    }







}
