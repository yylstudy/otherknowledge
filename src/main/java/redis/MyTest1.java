package redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-15
 */
public class MyTest1 {
    /**
     * guava 布隆过滤器使用
     */
    @Test
    public void test1(){
        int total = 1000000;
        //创建一个布隆过滤器，第二个参数为预放入的元素个数，第三个参数为误判率，默认为0.03
        //误判率就是发生hash碰撞，可以知道误判率越小，匹配的精度就越大，但是需要的存储空间也就越大
        BloomFilter bloomFilter = BloomFilter
                .create(Funnels.stringFunnel(Charsets.UTF_8),total,0.003);
        for(int i=0;i<total;i++){
            bloomFilter.put(String.valueOf(i));
        }
        int count = 0;
        for(int i=0;i<total+10000;i++){
            boolean b = bloomFilter.mightContain(String.valueOf(i));
            if(b){
                count++;
            }
        }
        System.out.println("误判个数为："+(count-total));
    }
    private BloomFilter filter = BloomFilter
            .create(Funnels.stringFunnel(Charsets.UTF_8),10,0.003);
    private Map<String,String> db = new HashMap<>();
    private Jedis jedis = JedisUtil.getJedis();
    /**
     * redis防止缓存穿透之布隆过滤器实现
     */
    @Test
    public void test2(){
        loadData();
        //缓存击中次数
        int random = new Random().nextInt(20);
        String key = String.valueOf(random);
        System.out.println("key:"+key);
        if(!filter.mightContain(key)){
            System.out.println("不存在数据");
            return;
        }
        String cacheValue = jedis.get(key);
        if(cacheValue!=null){
            System.out.println("redis中查询到缓存："+cacheValue);
            return;
        }else{
            jedis.set(key,db.get(key));
            System.out.println("数据库中查询到值："+db.get(key));
            return ;
        }

    }

    /**
     * redis防止缓存穿透之缓存空值
     */
    @Test
    public void test3(){
        loadData();
        int random = new Random().nextInt(20);
        String key = String.valueOf(random);
        System.out.println("key:"+key);
        String cacheValue = jedis.get(key);
        if(cacheValue!=null){
            System.out.println("redis中查询到缓存："+cacheValue);
            return;
        }else{
            String dbValue = db.get(key);
            if(dbValue!=null){
                jedis.set(key,dbValue);
                System.out.println("数据库中查询到值:"+dbValue);
            }else{
                System.out.println("数据库中不存在数据");
                jedis.set(key,"null");
            }
        }
    }

    /**
     * 防止缓存击穿，这个是针对热点key的
     * 主要是使用redis分布式锁
     */
    @Test
    public void test4() throws Exception{
        loadData();
        int random = new Random().nextInt(20);
        String key = String.valueOf(random);
        System.out.println("key:"+key);
        get(key);

    }

    private String get(String key) throws Exception{
        String cacheValue = jedis.get(key);
        if(cacheValue==null){
            //nx setNotExists 这里最好加个过期时间，后面防止del失败，缓存过期一直不能load
            //使用RedisTemplate，setnx返回1标识设置成功
            if (jedis.setnx(key,"1")==1){
                cacheValue = jedis.get(key);
                //再次判断是否为空
                if(cacheValue==null){
                    cacheValue = db.get(key);
                    jedis.set(key,cacheValue);
                    jedis.del(key);
                }
            }else{
                Thread.sleep(50);
                get(key);
            }
        }
        return cacheValue;
    }

    /**
     * 缓存雪崩主要是因为大量缓存数据同一时间内过期，
     * 1个方法就是对于数据设置不用的过期时间
     */
    public void test5(){

    }




    private void loadData(){
        for(int i=0;i<10;i++){
            filter.put(String.valueOf(i));
            db.put(String.valueOf(i),"yyl"+i);
        }
    }
}
