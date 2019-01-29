package serviceloader;

import java.util.ServiceLoader;

/**
 * @Author: yyl
 * @Date: 2018/11/21 18:04
 */
public class MyTest1 {
    /**
     * ServiceLoader测试，这个是有效获取子类的方式
     */
    public static void main(String[] args){
        for(MyInterface myInterface:ServiceLoader.load(MyInterface.class)){
            myInterface.test1();
        }
    }

}
