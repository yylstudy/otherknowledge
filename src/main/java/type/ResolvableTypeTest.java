package type;

import org.springframework.core.ResolvableType;

/**
 * @Author: yyl
 * @Date: 2019/4/3 16:02
 */
public class ResolvableTypeTest {
    public static void main(String[] args){
        ResolvableType typeToMatch = ResolvableType.forClass(MyTest2.class);
        typeToMatch.hasGenerics();
        System.out.println(111111111);
    }
    static class MyTest2<String,Integer>{

    }
    
}
