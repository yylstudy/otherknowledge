package type;

import java.lang.reflect.*;
import java.util.List;

/**
 * 通配符类型测试 这个表示仅仅类似于 ? extends T、? super K这样的通配符表达式
 * Type[] getUpperBounds():获取泛型表达式上限 获取泛型变量上边界（extends）
 * Type[] getLowerBounds():获取泛型表达式下限 获取泛型表达式的下边界(super)
 * @Author: yyl
 * @Date: 2019/1/21 9:46
 */
public class WildcardTypeTest {
    private List<? extends Number> a;
    private List<? super String> b;
    private List<String> c;
    private Class<?> clazz;

    /**
     * WildcardType 测试
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Field[] fields = WildcardTypeTest.class.getDeclaredFields();
        for(Field field:fields){
            Type type = field.getGenericType();
            if(type instanceof ParameterizedType){
                ParameterizedType type1 = (ParameterizedType) type;
                for(Type type2:type1.getActualTypeArguments()){
                    if(type2 instanceof WildcardType){
                        pringWildcardType(field,(WildcardType)type2);
                    }
                }
            }else if(type instanceof TypeVariable){
                TypeVariable typeVariable = (TypeVariable) type;
                System.out.println(field.getName()+"typeVariable :"+typeVariable);
            }else if(type instanceof WildcardType){
                pringWildcardType(field,(WildcardType)type);
            }
        }
    }

    private static void pringWildcardType(Field field, WildcardType type2) {
        for(Type type:type2.getUpperBounds()){
            System.out.println(field.getName()+" getUpperBounds:"+type);
        }
        for(Type type:type2.getLowerBounds()){
            System.out.println(field.getName()+" getLowerBounds:"+type);
        }
    }


}
