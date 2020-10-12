package locks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-29
 */
public class MyTest1 {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        CompletableFuture<String>[] ss = IntStream.range(0,10).mapToObj(index->CompletableFuture.supplyAsync(()->{
            for(int i=0;i<100000;i++){
                SpinLock.lock();
                myObject.add();
                SpinLock.unLock();
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
