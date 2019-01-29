package cglib;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @Author: yyl
 * @Date: 2019/1/16 17:36
 */
public class MyTest3 {
    /**
     * cglib动态生成属性测试
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setInterfaces(new Class<?>[] {BeanFactoryAware.class});
        enhancer.setUseFactory(false);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        //设置回调器，这个回调器，这个拦截方法不能直接调用被代理的方法
        enhancer.setCallback(new DaoProxyBeanFactoryAware());
        //设置BeanFactoryAware生成器，可以看到这里指定了生成的属性名为$$beanFactory
        enhancer.setStrategy(new BeanFactoryAwareGeneratorStrategy(BeanFactoryAware.class.getClassLoader()));

        //设置代理接口
        enhancer.setInterfaces(new Class[]{BeanFactoryAware.class});
        Dao dao = (Dao)enhancer.create();
        if(dao instanceof BeanFactoryAware){
            ((BeanFactoryAware) dao).setBeanFactory(new DefaultListableBeanFactory());
        }
        //调用BeanFactoryFactoryAware接口的setBeanFactory方法时，会在父类生成属性$$beanFactory
        Field field = ReflectionUtils.findField(dao.getClass(),"$$beanFactory");
        System.out.println(field.get(dao));
    }

}
