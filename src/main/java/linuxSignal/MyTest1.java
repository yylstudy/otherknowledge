package linuxSignal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @Author yang.yonglian
 * @ClassName: linuxSignal
 * @Description: TODO(这里描述)
 * @Date 2019/5/31 0031
 */
public class MyTest1 implements SignalHandler {
    @Override
    public void handle(Signal signal) {
        System.out.println(signal.getName()+" command is recevied");
    }
    public static void main(String[] args) throws Exception{
        MyTest1 myTest1 = new MyTest1();
        Signal.handle(new Signal("TERM"), myTest1);
        Signal.handle(new Signal("USR1"), myTest1);
        Signal.handle(new Signal("USR2"), myTest1);
        for (;;) {
            Thread.sleep(3000);
            System.out.println("running......");
        }
    }
}
