package com.alibaba.dubbo.rpc;

import com.alibaba.dubbo.common.bytecode.Wrapper;

import java.util.Map;

/**
 * DemoService接口的装饰类
 * @Author: yyl
 * @Date: 2019/3/7 16:56
 */
public class Wrapper1 extends Wrapper {
    public Wrapper1() {

    }

    /**
     * 接口所有public的field的属性名数组
     */
    public static String[] pns;
    /**
     * 接口所有public的field的属性名和其属性类型的映射
     */
    public static Map pts;
    /**
     * 接口包括其父类所有public的方法名数组
     */
    public static String[] mns;
    /**
     * 当前接口的所有public的方法名数组
     */
    public static String[] dmns;
    /**
     * 方法参数类型数组
     */
    public static Class[] mts0;

    public String[] getPropertyNames() {
        return pns;
    }

    public boolean hasProperty(String n) {
        return pts.containsKey(n);
    }

    public Class getPropertyType(String n) {
        return (Class) pts.get(n);
    }

    public String[] getMethodNames() {
        return mns;
    }

    public String[] getDeclaredMethodNames() {
        return dmns;
    }


    public void setPropertyValue(Object o, String n, Object v) {
        com.alibaba.dubbo.rpc.DemoServiceImpl w;
        try {
            w = ((com.alibaba.dubbo.rpc.DemoServiceImpl) o);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchPropertyException
                ("Not found property \"" + n + "\" filed or setter method in class com.alibaba.dubbo.rpc.DemoServiceImpl.");
    }


    public Object getPropertyValue(Object o, String n) {
        com.alibaba.dubbo.rpc.DemoServiceImpl w;
        try {
            w = ((com.alibaba.dubbo.rpc.DemoServiceImpl) o);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchPropertyException
                ("Not found property \"" + n + "\" filed or setter method in class com.alibaba.dubbo.rpc.DemoServiceImpl.");
    }

    /**
     * 调用具体方法
     * @param o
     * @param n
     * @param p
     * @param v
     * @return
     * @throws java.lang.reflect.InvocationTargetException
     */
    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws
            java.lang.reflect.InvocationTargetException {
        com.alibaba.dubbo.rpc.DemoServiceImpl w;
        try {
            w = ((com.alibaba.dubbo.rpc.DemoServiceImpl) o);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        try {
            if ("sayHello".equals(n) && p.length == 1) {
                return  w.sayHello((java.lang.String) v[0]);
            }
        } catch (Throwable e) {
            throw new java.lang.reflect.InvocationTargetException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchMethodException("Not found method \"" + n + "\" in class com.alibaba.dubbo.rpc.DemoServiceImpl.");
    }


}
