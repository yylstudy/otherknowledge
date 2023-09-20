package zookeeper2;

import collection.sourcecode.HashMap;
import collection.sourcecode.LinkedHashMap;

import java.util.Iterator;
import java.util.Map;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022/2/23 10:41
 */

public class MyTest1 {
    public static void main(String[] args) {
        Map map = new LinkedHashMap();
        map.put("name3","yyl3");
        map.put("name","yyl");
        map.put("name2","yyl2");
        map.put("name5","yyl2");
        map.put("name4","yyl2");
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = iterator.next();
            System.out.println(entry.getKey()+":"+entry.getValue()+":");
        }
    }
}
