package com.alibaba.dubbo.rpc;

import com.alibaba.dubbo.common.bytecode.Wrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Author: yyl
 * @Date: 2019/3/7 17:05
 */
public class MyTest5 {
    public static void main(String[] args) throws Exception{
        final Wrapper wrapper = Wrapper.getWrapper(DemoServiceImpl.class);
        Class clazz = wrapper.getClass();
        System.out.println(clazz.getName());
        System.out.println(clazz.getSuperclass().getName());
        for(Constructor constructor:clazz.getDeclaredConstructors()){
            System.out.println(constructor.getName());
            System.out.println(constructor.getParameterTypes().length);
        }
        for(Field field:clazz.getDeclaredFields()){
            System.out.println(field.getName());
            System.out.println(field.get(wrapper));
        }
        for(Method method:clazz.getDeclaredMethods()){
            System.out.println(method.getName());
        }
    }

}
