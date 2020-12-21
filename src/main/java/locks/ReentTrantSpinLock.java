package locks;


import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 不可重入自旋锁实现
 * @createTime 2020-09-29
 */
public class ReentTrantSpinLock {
    private AtomicReference reference = new AtomicReference();
    private int count;

    public void lock(){
        if(reference.get()==Thread.currentThread()){
            count++;
            return ;
        }
        while(!reference.compareAndSet(null,Thread.currentThread())){

        }

    }

    public void unlock(){
        if(reference.get()==Thread.currentThread()){
            if(count>0){
                count--;
                return;
            }
            reference.compareAndSet(Thread.currentThread(),null);
        }

    }

}
