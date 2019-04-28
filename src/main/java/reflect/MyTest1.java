package reflect;

import java.lang.reflect.Method;

/**
 * @Author: yyl
 * @Date: 2019/3/5 15:31
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        Integer i = 1;
        System.out.println(Number.class.isInstance(i));
    }

}
