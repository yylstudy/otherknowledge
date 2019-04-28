package hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 携带虚拟节点的一致性hash算法示例
 * @Author: yyl
 * @Date: 2019/3/14 10:39
 */
public class ConsistentHashingWithVirtualNode {
    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};
    /**
     * 虚拟节点的hash值和 服务器的映射集合
     */
    private static SortedMap<Integer,String> map = new TreeMap<>();
    /**
     * 每台服务器上虚拟节点个数
     */
    private static int virtualCount = 5;

    static{
        for(int i=0;i<virtualCount;i++){
            for(String str:servers){
                map.put(HashUtil.getHash(str+"&&VN"+i),str);
            }
        }
    }

    public static void main(String[] args){
        String[] keys = {"太阳", "月亮", "星星"};
        for(int i=0; i<keys.length; i++)
            System.out.println("[" + keys[i] + "]的hash值为" +
                    HashUtil.getHash(keys[i]) + ", 被路由到结点[" + getServer(HashUtil.getHash(keys[i])) + "]");
    }

    /**
     * 获取服务器
     * @param i
     * @return
     */
    public static String getServer(Integer i){
        SortedMap subMap = map.tailMap(i);
        if(subMap.isEmpty()){
            return map.get(map.firstKey());
        }else{
            return map.get(subMap.firstKey());
        }
    }
}
