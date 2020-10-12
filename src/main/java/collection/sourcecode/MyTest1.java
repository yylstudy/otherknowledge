package collection.sourcecode;


import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public class MyTest1 {
    /**
     * 校验hashmap的遍历
     */
    @Test
    public void test1(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("1",1);
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }
    }
    @Test
    public void test2(){
        Map<String,Object> map = new LinkedHashMap(16,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if(this.size>3){
                    return true;
                }
                return false;
            }
        };
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        System.out.println(map);
        map.put("4","4");
        map.get("3");
        System.out.println(map);


    }

}
