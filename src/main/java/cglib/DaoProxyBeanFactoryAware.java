package cglib;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @Author: yyl
 * @Date: 2019/1/16 14:26
 */
public class DaoProxyBeanFactoryAware implements MethodInterceptor {
    /**
     * 动态代理的方法，相当于JDK的InvocationHandler的invoke方法
     * @param obj cglib生成的代理对象
     * @param method 调用方法
     * @param args 被代理方法参数
     * @param methodProxy 调用父类方法的代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Field field = ReflectionUtils.findField(obj.getClass(),"$$beanFactory");
        field.set(obj,args[0]);
        //这里需要判断下，如果代理对象的父类也就是被代理的对象未实现BeanFactoryAware
        //那么就不需要执行器父类方法，也就是setBeanFactory方法
        if (BeanFactoryAware.class.isAssignableFrom(ClassUtils.getUserClass(obj.getClass().getSuperclass()))) {
            return methodProxy.invokeSuper(obj, args);
        }
        return null;
    }
}
