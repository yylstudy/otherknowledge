//package com.alibaba.dubbo.rpc;
//
///**
// * @Author: yyl
// * @Date: 2019/3/7 15:30
// */
//public class MyTest4 {
//    public void setPropertyValue(Object o, String n, Object v){
//        com.alibaba.dubbo.rpc.DemoServiceImpl w;
//        try{ w = ((com.alibaba.dubbo.rpc.DemoServiceImpl)$1); }catch(Throwable e){
//            throw new IllegalArgumentException(e); }
//        throw new com.alibaba.dubbo.common.bytecode.NoSuchPropertyException
//                ("Not found property \""+$2+"\" filed or setter method in class com.alibaba.dubbo.rpc.DemoServiceImpl."); }
//
//
//
//    public Object getPropertyValue(Object o, String n){
//        com.alibaba.dubbo.rpc.DemoServiceImpl w;
//        try{ w = ((com.alibaba.dubbo.rpc.DemoServiceImpl)$1); }catch(Throwable e){
//            throw new IllegalArgumentException(e); }
//        throw new com.alibaba.dubbo.common.bytecode.NoSuchPropertyException
//                ("Not found property \""+$2+"\" filed or setter method in class com.alibaba.dubbo.rpc.DemoServiceImpl."); }
//
//
//
//
//
//    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws
//            java.lang.reflect.InvocationTargetException{
//        com.alibaba.dubbo.rpc.DemoServiceImpl w;
//        try{ w = ((com.alibaba.dubbo.rpc.DemoServiceImpl)$1);
//        }catch(Throwable e){ throw new IllegalArgumentException(e); }
//        try{ if( "sayHello".equals( $2 )  &&  $3.length == 1 ) {
//            return ($w)w.sayHello((java.lang.String)$4[0]); }
//        } catch(Throwable e) {
//            throw new java.lang.reflect.InvocationTargetException(e);  }
//        throw new com.alibaba.dubbo.common.bytecode.NoSuchMethodException("Not found method \""+$2+"\" in class com.alibaba.dubbo.rpc.DemoServiceImpl."); }
//
//}
