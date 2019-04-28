package collection;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * TreeMap的使用
 * @Author: yyl
 * @Date: 2019/3/13 17:29
 */
public class MyTest1 {
    public static void main(String[] args){
        //创建一个TreeMap
        SortedMap<Integer,String> sortedMap = new TreeMap();
        sortedMap.put(3,"3");
        sortedMap.put(1,"1");
        sortedMap.put(2,"3");
        sortedMap.put(5,"5");
        sortedMap.put(4,"4");
        //获取最小值的key
        System.out.println(sortedMap.firstKey());
        //获取最大值的key
        System.out.println(sortedMap.lastKey());
        //获取key大于等于3的子map
        System.out.println(sortedMap.tailMap(3));

    }

}
