package classloader;

/**
 * @Author: yyl
 * @Date: 2018/9/21 17:00
 */
public class MyTest1 {
    public static void main(String[] args){
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
    public void test2(){
        System.out.println("hello world");
    }
    
}
