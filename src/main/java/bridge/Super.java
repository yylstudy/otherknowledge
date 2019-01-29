package bridge;

/**
 * @Author: yyl
 * @Date: 2018/8/22 19:37
 */
public class Super<T> {
    public void test1(T t){
        System.out.println(t);
    }
    public void test2(String t){
        System.out.println(t);
    }
}
