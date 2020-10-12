package resolvableType;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/9 10:52
 * @Version: 1.0
 */
public class MyTest1 {
    public static void main(String[] args) {
        ResolvableType type = ResolvableType.forField(ReflectionUtils.findField(PersonService.class,"baseDao"),1,PersonService.class);
        ResolvableType type1 = ResolvableType.forClass(PeronDao.class);
        System.out.println(type.isAssignableFrom(type1));
        System.out.println(Configuration.class.isAnnotationPresent(Component.class));;
    }
}
