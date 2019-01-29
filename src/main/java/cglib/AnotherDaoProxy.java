package cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: yyl
 * @Date: 2019/1/16 14:44
 */
public class AnotherDaoProxy implements MethodInterceptor {
    /**
     * cglib的增强方法
     * @param o 被代理的对象
     * @param method 被代理的方法
     * @param objects 被代理方法参数
     * @param methodProxy 代理方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start time "+System.currentTimeMillis());
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("end time "+System.currentTimeMillis());
        return result;
    }
}
