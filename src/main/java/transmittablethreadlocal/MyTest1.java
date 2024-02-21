package transmittablethreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description transmittable-thread-local使用教程
 * @createTime 2024/2/21 9:51
 */

public class MyTest1 {
    public static void main(String[] args) {
        ThreadLocal<String> context2 = new ThreadLocal<>();
        context2.set("123456");
        new Thread(()->{
            String str = context2.get();
            System.out.println(str); //输出为null
        }).start();

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("123456");
        new Thread(()->{
            String str = context.get();
            System.out.println(str);//输出123456
        }).start();
    }
}
