package listenableFuture;

import com.google.common.util.concurrent.*;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用guava的方式去异步执行任务，并返回结果
 * @Author: yyl
 * @Date: 2018/12/12 11:05
 */
public class MyTest1 {
    private static ExecutorService pool = Executors.newCachedThreadPool();

    /**
     * 使用传统的Future的方式去获取结果，缺点是阻塞
     * @throws Exception
     */
    @Test
    public void test1() throws Exception{
        long start = System.currentTimeMillis();
        List<Future> list = new ArrayList<Future>();
        for(int i=10;i>0;i--){
            list.add(pool.submit(new MyCallable(i)));
        }
        for(Future future:list){
            System.out.println(future.get());
        }
    }

    /**
     * 使用CompletionService来实现异步获取结果
     * 从这里可以看出completionService是以当前对象为单位的
     * 并不会获取其他提交到该线程池的任务
     * @throws Exception
     */
    @Test
    public void test2() throws Exception{
        CompletionService completionService = new ExecutorCompletionService<Integer>(pool);
        List<Future> list = new ArrayList<Future>();
        for(int i=5;i>0;i--){
            completionService.submit(new MyCallable(i));
        }
        CompletionService completionService2 = new ExecutorCompletionService<Integer>(pool);
        for(int i=5;i>0;i--){
            completionService2.submit(new MyCallable(i));
        }
        for(int i=10;i>0;i--){
            System.out.println(completionService.take().get());
        }

    }

    /**
     * 使用guava的listenableFuture异步获取结果
     * @throws Exception
     */
    @Test
    public void test3() throws Exception{
        final List<ListenableFuture> list = new ArrayList();
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        for(int i=5;i>0;i--){
            ListenableFuture listenableFuture = pool.submit(new MyCallable(i));
            Futures.addCallback(listenableFuture,new FutureCallback<Integer>() {
                public void onSuccess(Integer result) {
                    System.out.println(result);
                }
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
            list.add(listenableFuture);
        }
        for(ListenableFuture future:list){
            future.get();
        }
    }


    @AllArgsConstructor
    static class MyCallable implements Callable<Integer>{
        private int sleepTime;
        public Integer call() throws Exception {
            Thread.sleep(sleepTime*1000);
            return sleepTime;
        }
    }

}
