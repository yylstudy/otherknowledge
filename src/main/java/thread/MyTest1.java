package thread;

import org.junit.Test;


/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-12
 */
public class MyTest1 {
    @Test
    public void test1() throws Exception{
        Thread t1 = new Thread(()->{
            sleep(1000);
            System.out.println("t1线程执行完毕");
        });
        t1.start();
        Thread t2 = new Thread(()->{
            try{
                t1.join();
                System.out.println("t2线程执行完毕");
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
        t2.start();
        sleep(5000);
    }



    private void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
