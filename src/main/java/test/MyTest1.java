package test;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @Author: yyl
 * @Date: 2019/3/14 17:04
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception {
        String str = System.getProperty("user.home");
        System.out.println(str);
    }
}
