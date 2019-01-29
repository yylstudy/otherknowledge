package cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @Author: yyl
 * @Date: 2019/1/16 15:07
 */
public class DaoFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        //select 方法使用第一个回调
        if(method.getName().equals("select")){
            return 0;
        }
        //其余方法使用第二个回调
        return 1;
    }
}
