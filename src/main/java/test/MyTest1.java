package test;


import org.junit.Test;

/**
 * @Author: yyl
 * @Date: 2019/3/14 17:04
 */
public class MyTest1 {
    @Test
    public void test1(){
        Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        Integer n3 = Integer.valueOf(47);
        Integer n4 = Integer.valueOf(47);
        Integer n5 = Integer.valueOf(129);
        Integer n6 = Integer.valueOf(129);
        System.out.print(n1 == n2);
        System.out.print(",");
        System.out.println(n1 != n2);
        System.out.print(n1 == n3);
        System.out.print(n4 == n3);
        System.out.print(n5 == n6);
    }

    /**
     * 装箱与拆箱测试
     */
    @Test
    public void test2(){
        System.out.println(inc());
    }

    /**
     * 方法重载测试
     */
    @Test
    public void test3(){
        Human man = new Man();
        Human woman = new Woman();
        sayHello(man);
        sayHello(woman);
    }

    /**
     * 方法动态绑定测试，方法具有多态性
     */
    @Test
    public void test4(){
        Human man = new Man();
        Human woman = new Woman();
        man.run();
        woman.run();
    }

    /**
     * 属性多态测试，属性没有多态性
     */
    @Test
    public void test5(){
        Human man = new Man();
        Human woman = new Woman();
        System.out.println(man.height);
        System.out.println(woman.height);
    }

    /**
     * 装箱测试
     */
    @Test
    public void test6(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }

    /**
     * volatile使用
     */
    @Test
    public void test7(){

    }

    static abstract class Human {
        public int height = 1;
        public Human(){
            height = 2;
            showHeight();
        }
        public void showHeight(){
            System.out.println("i am human height:"+height);
        }
        void run(){
            System.out.println("a human is running");
        }
    }
    static class Man extends Human {
        public int height = 3;
        public Man(){
            height = 4;
            showHeight();
        }
        @Override
        void run() {
            System.out.println("a man is running");
        }

        @Override
        public void showHeight() {
            System.out.println("i am man height:"+height);
        }
    }
    static class Woman extends Human {
        public int height = 5;
        public Woman(){
            height = 6;
            showHeight();
        }
        @Override
        void run() {
            System.out.println("a woman is running");
        }

        @Override
        public void showHeight() {
            System.out.println("i am woman height:"+height);
        }
    }


    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public int inc() {
        int x;
        try {
            x = 1;
//            if(true){
//                throw new RuntimeException();
//            }
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }


}
