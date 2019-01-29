package cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

/**
 * @Author: yyl
 * @Date: 2019/1/16 14:39
 */
public class MyTest2 {
    /**
     * spring-cglib的高级使用，针对不同方法使用不同的回调处理
     * @param args
     */
    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        //这里设置多个回调，最后一次回调是空的Callback，即不想对某个方法进行拦截
        //存在多个回调时，是和CallbackFilter配套使用的，根据CallbackFilter的accept方法返回的下标一一对应
        enhancer.setCallbacks(new Callback[] {new DaoProxy(),new AnotherDaoProxy(),NoOp.INSTANCE});
        enhancer.setCallbackFilter(new DaoFilter());
        Dao dao = (Dao)enhancer.create();
        dao.select();
        dao.update();
    }

}
