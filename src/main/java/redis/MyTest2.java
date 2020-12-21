package redis;

import redis.clients.jedis.Jedis;

//
///**
// * @author yang.yonglian
// * @version 1.0.0
// * @Description redis实现延迟队列，使用zset，score存储的是过期时间的时间戳，不断get当前队列，如果score大于
// * 当前时间戳就取出
// * @createTime 2020-10-19
// */
public class MyTest2 {
    public static void main(String[] args) {

    }
}
