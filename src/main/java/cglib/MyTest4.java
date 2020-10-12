package cglib;

import collection.sourcecode.HashMap;
import org.springframework.cglib.proxy.Enhancer;

import java.util.Map;

/**
 * @Author: yyl
 * @Date: 2019/1/23 15:00
 */
public class MyTest4 {
    /**
     * spring aop的生成方式拦截测试，不拦截方法的内部调用
     * @param args
     */
    public static void main(String[] args){
        //注意这里的被代理对象
        Dao dao = new Dao();
        SpringDaoProxy daoProxy = new SpringDaoProxy(dao);
        Enhancer enhancer = new Enhancer();
        enhancer.setInterceptDuringConstruction(false);
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);
        Dao dao1 = (Dao)enhancer.create();
        //方法内部调用无效，关键原因在于SpringDaoProxy的回调方法
        System.out.println(dao1.select());
        System.out.println(dao1.update());
        Map<String,Object> map = new HashMap<>();
        String  ss = (String)map.get("ss");
    }

}
