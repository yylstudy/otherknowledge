package com.alibaba.dubbo.rpc;

/**
 * @Author: provider
 * @Date: 2019/2/21 14:24
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) throws Exception{
//        Thread.sleep(2000);
        return "hello "+name;
    }
}
