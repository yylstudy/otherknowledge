//package com.alibaba.dubbo.common.bytecode;
//
//import java.lang.reflect.InvocationHandler;
//
///**
// * @Author: yyl
// * @Date: 2019/3/24 15:23
// */
//public class proxy0 implements org.apache.dubbo.demo.DemoService,com.alibaba.dubbo.rpc.service.EchoService{
//    public proxy0(){
//
//    }
//    public static java.lang.reflect.Method[] methods;
//    private InvocationHandler handler;
//    public proxy0(java.lang.reflect.InvocationHandler arg0){
//        handler = $1;
//    }
//
//    public java.lang.String sayHello(java.lang.String arg0) throws java.lang.Exception {
//        Object[] args = new Object[1];
//        args[0] = ($w) $1;
//        Object ret = handler.invoke(this, methods[0], args);
//        return (java.lang.String) ret;
//    }
//
//    public java.lang.Object $echo(java.lang.Object arg0) {
//        Object[] args = new Object[1];
//        args[0] = ($w) $1;
//        Object ret = handler.invoke(this, methods[1], args);
//        return (java.lang.Object) ret;
//    }
//}
