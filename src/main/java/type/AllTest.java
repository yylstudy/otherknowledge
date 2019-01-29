package type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取父类、父接口中的泛型参数
 *
 * @Author: yyl
 * @Date: 2019/1/21 10:25
 */
public class AllTest extends Parent<String> implements IParent<Long> {
    public static void main(String[] args) {
        //获取父类中的泛型
        Type genericSuperclass = AllTest.class.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericSuperclass;
            for (Type type1 : type.getActualTypeArguments()) {
                System.out.println("parent class is ParameterizedType and getActualTypeArguments is" + type1);
            }
        }
        //获取接口中的泛型
        Type[] genericInterfaceclasss = AllTest.class.getGenericInterfaces();
        for (Type type : genericInterfaceclasss) {
            if (type instanceof ParameterizedType) {
                ParameterizedType type1 = (ParameterizedType) type;
                for (Type type2 : type1.getActualTypeArguments()) {
                    System.out.println("interface IParent is Parameterized and getActualTypeArguments is" + type2);
                }
            }
        }

    }

}
