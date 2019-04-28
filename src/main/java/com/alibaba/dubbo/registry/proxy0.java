//package com.alibaba.dubbo.registry;
//
//import com.alibaba.dubbo.common.bytecode.ClassGenerator;
//import com.alibaba.dubbo.rpc.DemoService;
//import com.alibaba.dubbo.rpc.service.EchoService;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
///**
// * @Author: yyl
// * @Date: 2019/3/25 17:18
// */
//public class proxy0 implements ClassGenerator.DC, EchoService, DemoService {
//    // 方法数组
//    public static Method[] methods;
//    private InvocationHandler handler;
//
//    public proxy0(InvocationHandler invocationHandler) {
//        this.handler = invocationHandler;
//    }
//
//    public proxy0() {
//    }
//
//    public String sayHello(String string) throws Throwable{
//        // 将参数存储到 Object 数组中
//        Object[] arrobject = new Object[]{string};
//        // 调用 InvocationHandler 实现类的 invoke 方法得到调用结果
//        Object object = this.handler.invoke(this, methods[0], arrobject);
//        // 返回调用结果
//        return (String)object;
//    }
//
//    /** 回声测试方法 */
//    public Object $echo(Object object) {
//        Object[] arrobject = new Object[]{object};
//        Object object2 = this.handler.invoke(this, methods[1], arrobject);
//        return object2;
//    }
//}
