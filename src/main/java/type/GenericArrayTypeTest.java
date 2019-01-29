package type;

import org.junit.runners.Parameterized;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * 泛型数组，描述的形如A<T>[]或者T[]类型
 * @Author: yyl
 * @Date: 2019/1/18 17:48
 */
public class GenericArrayTypeTest<T> {
    /**
     *
     * @param pTypeArray GenericArrayType type:java.util.List<java.lang.String>[]
     *                   genericComponentType:java.util.List<java.lang.String>
     * @param vTypeArray GenericArrayType type: T[]
     *                   genericComponentType:T
     * @param list ParameterizedType type:java.util.List<java.lang.String>
     * @param strings type:[Ljava.lang.String;
     * @param test test type :class [Ltype.GenericArrayTypeTest;
     */
    public void testGenericArrayType(List<String>[] pTypeArray,T[] vTypeArray,
                                     List<String> list,String[] strings,GenericArrayTypeTest[] test){

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Method[] methods = GenericArrayTypeTest.class.getDeclaredMethods();
        for(Method method:methods){
            if(method.getName().equals("main")){
                continue;
            }
            Type[] types = method.getGenericParameterTypes();
            for(Type type:types){
                if(type instanceof ParameterizedType){
                    System.out.println("ParameterizedType type :"+type);
                }else if(type instanceof GenericArrayType){
                    System.out.println("GenericArrayType type :"+type);
                    //GenericArrayType.getComponentType()方法返回的是泛型数组元素的Type类型
                    //例如 List<String>[] 中的是List<String>，T[] 中的是T
                    //需要注意的是无论是几维数组，getComponentType()方法都只会去除最右边的[],返回剩下的值
                    Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                    System.out.println("genericComponentType:"+genericComponentType);
                }else if(type instanceof WildcardType){
                    System.out.println("wildcardType:"+type);
                }else if(type instanceof TypeVariable){
                    System.out.println(" typeVariable type:"+type);
                }else{
                    System.out.println("type:"+type);
                }
            }
        }
    }
}
