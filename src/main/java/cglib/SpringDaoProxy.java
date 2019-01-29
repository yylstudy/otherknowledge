package cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: yyl
 * @Date: 2019/1/23 15:00
 */
public class SpringDaoProxy implements MethodInterceptor {
    public SpringDaoProxy(Object target) {
        this.target = target;
    }

    /**
     * 被代理对象，这个是模仿spring aop中生成代理对象的方式
     */
    private Object target;
    /**
     * 拦截方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method invoke");
        //回调方法 直接将被代理对象传入，这也是spring aop的实现方式，这样方法内部调用拦截方法失效了
        Object obj = methodProxy.invoke(target,objects);
        System.out.println("after method invoke");
        return obj;
    }
}
