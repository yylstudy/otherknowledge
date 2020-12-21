package locks;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-29
 */
public class MyTest1 {
    /**
     * 不可重入锁测试
     */
    @Test
    public void test1(){
        MyObject myObject = new MyObject();
        UnReentTrantSpinLock unReentTrantSpinLock = new UnReentTrantSpinLock();
        CompletableFuture<String>[] ss = IntStream.range(0,10).mapToObj(index->CompletableFuture.supplyAsync(()->{
            for(int i=0;i<100000;i++){
                unReentTrantSpinLock.lock();
                myObject.add();
                unReentTrantSpinLock.unlock();
            }
            return "";
        })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(ss).join();
        System.out.println(myObject.sum);
    }

    /**
     * 可重入锁测试
     */
    @Test
    public void test2(){
        MyObject myObject = new MyObject();
        ReentTrantSpinLock reentTrantSpinLock = new ReentTrantSpinLock();
        CompletableFuture<String>[] ss = IntStream.range(0,10).mapToObj(index->CompletableFuture.supplyAsync(()->{
            for(int i=0;i<100000;i++){
                reentTrantSpinLock.lock();
                myObject.add();
                reentTrantSpinLock.unlock();
            }
            return "";
        })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(ss).join();
        System.out.println(myObject.sum);
    }

    static class MyObject{
        public int sum;
        public void add(){
            sum++;
        }
    }
}
