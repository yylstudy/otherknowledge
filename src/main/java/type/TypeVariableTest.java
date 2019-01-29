package type;

import org.junit.runners.Parameterized;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.List;

/**
 * @Author: yyl
 * @Date: 2019/1/18 18:55
 */
public class TypeVariableTest<T extends Number & Serializable,V> {
    /**
     * TypeVariable
     */
    private T key;
    /**
     * TypeVariable
     */
    private V value;
    /**
     * GenericArrayType V[] ->V TypeVariable 两种混合起来了
     */
    private V[] values;
    /**
     * 原始类型，不仅包括我们平常所指的类，还包括枚举、数组、注解、基本类型等
     */
    private String str;
    /**
     * ParameterizedType List<T> ->T TypeVariable两种混合起来
     */
    private List<T> tList;
    
    public static void main(String[] args) throws Exception{
        Field[] fields = TypeVariableTest.class.getDeclaredFields();
        for(Field field:fields){
            Type type = field.getGenericType();
            if(type instanceof ParameterizedType){
                ParameterizedType type1 = (ParameterizedType)type;
                System.out.println("field "+field.getName()+" is ParameterizedType ");
                for(Type type2:type1.getActualTypeArguments()){
                    if(type2 instanceof TypeVariable){
                        printTypeVariable(field,(TypeVariable)type2);
                    }
                }
                System.out.println(field.getName()+" getOwnerType :"+type1.getOwnerType());
                System.out.println(field.getName()+" getRawType:"+type1.getRawType());
            }else if(type instanceof GenericArrayType){
                GenericArrayType arrayType = (GenericArrayType)type;
                System.out.println("genericArrayType type:"+arrayType);
                Type componentType = arrayType.getGenericComponentType();
                if(componentType instanceof TypeVariable){
                    printTypeVariable(field,(TypeVariable)componentType);
                }
            }else if(type instanceof TypeVariable){
                printTypeVariable(field,(TypeVariable)type);
            }

        }
    }

    /**
     * Type[] getBounds类型对应的上限，默认为Object
     * D getGenericDeclaration() 获取声明该类型的变量实体，也就是TypeVariableTest<T>中的TypeVariableTest
     * String getName 获取Type的名称
     * @param typeVariable
     */
    public static void printTypeVariable(Field field,TypeVariable typeVariable){
        for(Type type:typeVariable.getBounds()){
            System.out.println(field.getName()+":typeVariable getBdounds "+type);
        }
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        System.out.println("class getGenericDeclaration:"+genericDeclaration);
        System.out.println(typeVariable.getName());
    }
    
}
