package locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-29
 */
public class MyTest2 {
    public static void main(String[] args) {
        AtomicReference reference = new AtomicReference(null);
        boolean ss = reference.compareAndSet(null,new Object());
        System.out.println(ss);
        ss = reference.compareAndSet(null,"111");
        System.out.println(ss);
        System.out.println(reference.get());
    }
}
