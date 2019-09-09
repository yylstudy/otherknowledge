package cglib;

/**
 * @Author: yyl
 * @Date: 2019/1/16 14:25
 */
public class Dao {

    public String update(){
        System.out.println("do update");
        return " update success ";
    }

    public String select(){
        System.out.println("do select");
        //这里的方法内部调用在cglib中是可以被代理的，因当前对象this就是被代理的对象
        //但是基于cglib实现的spring aop切面不行，因为那个this对象是被代理对象，至于为什么
        //得看cglib的具体源码
        innerMethod();
        return "select success";
    }

    /**
     * 方法内部调用
     */
    public void innerMethod(){
        System.out.println("inner method");
    }

}
