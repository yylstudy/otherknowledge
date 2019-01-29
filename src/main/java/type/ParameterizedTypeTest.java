package type;

import javax.xml.ws.Holder;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * ParameterizedType使用测试，参数化类型
 * @Author: yyl
 * @Date: 2019/1/18 14:45
 */
public class ParameterizedTypeTest {
    /**
     * map:getActualTypeArguments(): class java.lang.String
     * map:getActualTypeArguments(): class type.ParameterizedTypeTest
     * map:getOwnerType()  is null
     * map:getRawType: interface java.util.Map
     */
    private Map<String,ParameterizedTypeTest> map;

    /**
     * set:getActualTypeArguments(): class java.lang.String
     * set:getOwnerType() is null
     * set:getRawType(): interface java.util.Set
     */
    private Set<String> set;
    /**
     * holder:getActualTypeArguments(): class java.lang.String
     * //这个应该是内部类的所在的类
     * holder:getOwnerType(): class type.ParameterizedTypeTest
     * holder:getRawType: class type.ParameterizedTypeTest$Holder
     */
    private Holder<String> holder;

    /**
     * entry:getActualTypeArguments(): class java.lang.String
     * entry:getActualTypeArguments(): class java.lang.String
     * entry:getOwnerType:interface java.util.Map
     * entry:getRawType: class java.util.Map$Entry
     */
    private Map.Entry<String,String> entry;

    /**
     * is not ParameterizedType
     */
    private String str;


    public static class Holder<V>{

    }


    public static void main(String[] args){
        Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
        for(Field field:fields){
            Type type = field.getGenericType();
            if(type instanceof ParameterizedType){
                ParameterizedType type1 = (ParameterizedType)type;
                Type[] types = type1.getActualTypeArguments();
                for(Type type2:types){
                    System.out.println("field "+field.getName()+" get ActualTypeArguments type is"+type2);
                }
                Type rawType = type1.getRawType();
                System.out.println("field "+field.getName()+" raw type is "+rawType);
                Type ownerType = type1.getOwnerType();
                if(ownerType==null){
                    System.out.println("field "+field.getName()+" ownerType is null");
                }else{
                    System.out.println("field "+field.getName()+" ownerType is "+ownerType);
                }
            }else{
                System.out.println("field "+field.getName()+" is not ParameterizedType");
            }
        }
    }



}
