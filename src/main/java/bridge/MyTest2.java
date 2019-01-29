package bridge;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: yyl
 * @Date: 2018/8/22 20:40
 */
public class MyTest2 {
    public static void main(String[] args){
        Type[] types = Test.class.getGenericInterfaces();
        for(Type type:types){
            Type[] type2 = ((Class)type).getGenericInterfaces();
            for(Type ty :type2){
                System.out.println(ty instanceof ParameterizedType);
                System.out.println(ty instanceof Class);
            }
        }

    }
    class Test implements Cloneable,Serializable{

    }
}
