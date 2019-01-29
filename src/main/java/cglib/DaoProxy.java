package cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * @Author: yyl
 * @Date: 2019/1/16 14:26
 */
public class DaoProxy implements MethodInterceptor {
    /**
     * 动态代理的方法，相当于JDK的InvocationHandler的invoke方法
     * @param o cglib生成的代理对象
     * @param method 调用方法
     * @param objects 被代理方法参数
     * @param methodProxy 调用父类方法的代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method invoke");
        //执行超类方法，也就是被代理对象的方法
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("after method invoke");
        return result;
    }
}
