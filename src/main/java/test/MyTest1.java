package test;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;

import java.net.InetAddress;
import java.util.concurrent.*;

/**
 * @Author: yyl
 * @Date: 2019/3/14 17:04
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, new NamedThreadFactory("test"));
        executorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("-----------------");
            }
        },5000,5000, TimeUnit.MILLISECONDS);

    }

}
