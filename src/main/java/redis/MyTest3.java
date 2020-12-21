package redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-12-10
 */
public class MyTest3 {
    public static void main(String[] args) {
        RLock redisson = Redisson.create().getLock("");
        redisson.lock();

        redisson.unlock();
    }
}
