package thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-13
 */
public class CountDownLatchTest {
    @Test
    public void test1() throws Exception{
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(100);
        ExecutorService pool = Executors.newFixedThreadPool(100);
        IntStream.range(0,100).forEach(index->{
            pool.execute(()->{
                try{
                    System.out.println("i am start");
                    start.await();
                    System.out.println("i am "+index);
                    end.countDown();
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            });
        });
        start.countDown();
        end.await();
        System.out.println("i am finish");
    }
}
