package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/5 10:59
 * @Version: 1.0
 */
public class DistributedLockUtils {
    public static void doLockWork(String zkAddress, String lockNode, ZteThread zteThread) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().
                connectString(zkAddress).
                connectionTimeoutMs(15 * 1000).
                sessionTimeoutMs(60 * 100).
                retryPolicy(retryPolicy).
                build();
        InterProcessMutex lock = new InterProcessMutex(client, lockNode);
        try{
            client.start();
            lock.acquire();
            zteThread.run();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (lock.isAcquiredInThisProcess()) {
                lock.release();
            }
            client.close();
        }
    }
}
