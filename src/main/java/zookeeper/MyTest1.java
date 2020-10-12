package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Description: 基于zk实现分布式锁
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/5 11:04
 * @Version: 1.0
 */
public class MyTest1 {
    public static void main(String[] args) {
        new Thread(()->test1()).start();
        new Thread(()->test1()).start();
    }
    public static void test1() {
        try{
            String lockNode = "/gx_wlw_lock_node";
            DistributedLockUtils.doLockWork("127.0.0.1:2181",lockNode,
                    ()->{
                        Thread.sleep(2000);
                        System.out.println("---------------------");
                    });

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
