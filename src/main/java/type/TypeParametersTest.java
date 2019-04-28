package type;

import java.lang.reflect.TypeVariable;

/**
 * @Author: yyl
 * @Date: 2019/4/3 15:43
 */
public class TypeParametersTest {
    public static void main(String[] args){
        TypeVariable[] typeVariables = MyTest1.class.getTypeParameters();
        for(TypeVariable typeVariable:typeVariables){
            System.out.println(typeVariable.getName());
        }
    }
    
    static class MyTest1<String,Integer>{
        
    }
}
