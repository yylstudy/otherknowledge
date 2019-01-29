package classloader;

import java.lang.reflect.Method;

/**
 * @Author: yyl
 * @Date: 2018/9/25 9:12
 */
public class MyTest2 {
    public static void main(String[] args) throws Exception{
        /**创建自定义的ClassLoader对象*/
        CustomClassLoader customClassLoader = new CustomClassLoader("D:\\");
        /**加载class文件，这个方法的内部会调用我们之前自定义的ClassLoader的findClass方法*/
        Class clazz = customClassLoader.loadClass("classloader.MyTest1");
        if(clazz!=null){
            try{
                Object obj = clazz.newInstance();
                Method method = clazz.getDeclaredMethod("test2");
                method.invoke(obj);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
