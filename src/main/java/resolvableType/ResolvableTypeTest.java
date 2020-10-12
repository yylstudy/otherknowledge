package resolvableType;

import org.junit.Test;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/17 17:01
 * @Version: 1.0
 */
public class ResolvableTypeTest {
    @Test
    public void test1(){
        ResolvableType typeToMatch = ResolvableType.forRawClass(BaseDao.class);
        boolean hasGenerics = typeToMatch.hasGenerics();
        System.out.println(hasGenerics);
        Class clazz = typeToMatch.resolve();
        System.out.println(clazz);
    }

    public Field getFields(Class clazz,String filedName){
        for(Field field:clazz.getDeclaredFields()){
            if(field.getName().equals(filedName)){
                return field;
            }
        }
        while (clazz.getSuperclass()!=Object.class){
            return getFields(clazz.getSuperclass(),filedName);
        }
        return null;
    }
}
