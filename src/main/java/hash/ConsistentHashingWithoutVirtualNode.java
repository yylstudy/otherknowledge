package hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 不带虚拟节点的一致性hash算法示例
 * @Author: yyl
 * @Date: 2019/3/14 10:16
 */
public class ConsistentHashingWithoutVirtualNode {
    /**
     * 需要添加到hash环的服务器列表
     */
    private static String[] servers = { "192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111" };
    /**
     * 服务器hash值和服务器的映射
     */
    private static SortedMap<Integer,String> map = new TreeMap();
    static{
        for(String str:servers){
            map.put(HashUtil.getHash(str),str);
        }
    }

    /**
     * 通过hash值获取对应的服务器
     * @param i
     * @return
     */
    public static String getServer(Integer i){
        //获取大于该hash值的所有列表
        SortedMap subMap = map.tailMap(i);
        //如果为空，根据一致性hash算法（顺时针）就从头往上查找 所以就是第一个
        if(subMap.isEmpty()){
            return map.get(map.firstKey());
        }else{
            //不为空就取最近的那个服务器
            return map.get(subMap.firstKey());
        }
    }

    public static void main(String[] args){
        String[] keys = {"太阳", "月亮", "星星"};
        for(String key:keys){
            System.out.println(getServer(HashUtil.getHash(key)));
        }
    }





}
