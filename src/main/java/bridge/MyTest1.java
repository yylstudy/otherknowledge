package bridge;

import java.lang.reflect.Method;

/**
 * 桥接方法说明：在泛型子类中重写父类的泛型方法时，编译器会自动生成一个桥接方法
 * 这是为了兼容jdk1.5之前的没出现泛型时的做法
 * mybatis源码中的创建反射器的时候去除重复的方法
 * 是不是也是这个呢？？？
 * @Author: yyl
 * @Date: 2018/8/22 19:37
 */
public class MyTest1 extends Super<String>{
    @Override
    public void test1(String s) {
        System.out.println("1");
    }
    public static void main(String[] args) throws Exception{
        /**
         * 可以看到这里其实有三个方法
         * 1）main方法
         * 2）test1方法 原生方法
         * 3）test1方法 桥接方法
         */
        Method[] methods = MyTest1.class.getDeclaredMethods();
        System.out.println(methods[1].equals(methods[2]));
        for(Method method:methods ){
            System.out.println(method.isBridge());
            System.out.println(method.getName());
            /**
             * spring源码中的桥接方法转实际方法
             */
            Method methos = BridgeHelp.findBridgedMethod(method);
            System.out.println(methos.isBridge());
            System.out.println(methos.getName());
            System.out.println("--------------------");
        }
    }


}
