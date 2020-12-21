package locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 不可重入自旋锁实现
 * @createTime 2020-09-29
 */
public class UnReentTrantSpinLock {
    private AtomicReference reference = new AtomicReference(null);
    public  void lock(){
        while (!reference.compareAndSet(null,Thread.currentThread())){

        }
//        System.out.println("get lock success");
    }

    public  void unlock(){
        reference.compareAndSet(Thread.currentThread(),null);
//        System.out.println("un lock success");
    }

}
