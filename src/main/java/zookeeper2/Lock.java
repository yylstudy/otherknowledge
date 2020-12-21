package zookeeper2;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-13
 */
public interface Lock {
    boolean lock();
    boolean unlock();
}
