package locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 自旋锁实现
 * @createTime 2020-09-29
 */
public class SpinLock {
    private static AtomicReference reference = new AtomicReference(null);
    private static Object object = new Object();
    public static void lock(){
        while (!reference.compareAndSet(null,object)){

        }
//        System.out.println("get lock success");
    }

    public static void unLock(){
        reference.compareAndSet(object,null);
//        System.out.println("un lock success");
    }

}
