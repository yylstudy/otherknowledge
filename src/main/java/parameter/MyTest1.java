package parameter;

import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @Description: 方法参数名称测试
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/8 17:18
 * @Version: 1.0
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        Constructor constructor = ParameterInfo.class.getConstructor(String.class,String.class);
        String[] paramNames = discoverer.getParameterNames(constructor);
        Arrays.stream(paramNames).forEach(System.out::println);
    }
}
