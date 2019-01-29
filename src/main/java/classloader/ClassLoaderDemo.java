package classloader;

/**
 * ClassLoader学习：
 * ClassLoader的具体作用就是将class文件加载到jvm中
 * classLoader具体分为三类：
 * 1）BootstrapClassLoader，最顶层的加载类，主要加载核心类库，%JAVA_HOME%\jre\lib下的jar包和class文件
 *   加载的jar包就是加载  System.getProperty("sun.boot.class.path")的jar
 * 2）ExtentionClassLoader，拓展的类加载器，主要加载%JAVA_HOME\jre\lib\ext目录下的jar包和class文件%
 *   加载的jar包就是加载  System.getProperty("java.ext.dirs")的jar
 * 3）AppClassLoader，加载当前应用的classpath的所有类
 *   加载的jar包就是加载  System.getProperty("java.class.path")的jar
 * @Author: yyl
 * @Date: 2018/9/21 16:55
 */
public class ClassLoaderDemo {
    public static void main(String[] args){
        ClassLoader classLoader = MyTest1.class.getClassLoader();
        /**输出ClassLoader is:sun.misc.Launcher$AppClassLoader@18b4aac2
         * 这个就是说明MyTest1是由AppClassLoader加载的，因为这是我们自己编写的类
         * */
        System.out.println("MyTest1 ClassLoader is:"+classLoader.toString());
        /**这里抛出空指针异常，String.class是由Bootstrap ClassLoader加载的，那么为什么会报错呢
         * 这是因为每个类加载器也就是ClassLoader都有一个父加载器，通过调用 classLoader.getParent()方法可以获取父类父类加载器
         * MyTest1的加载器是AppClassLoader，AppClassLoader的父加载器是ExtClassLoader
         * 那么ExtClassLoader的父加载器是什么呢，是空的，那么之前为什么又说每一个加载器都有一个父加载器呢？这不是矛盾吗？
         * 这是因为父加载器不是父类，看收藏的博客
         * */
        System.out.println(classLoader.getParent().toString());
        System.out.println("String ClassLoader is:"+String.class.getClassLoader().toString());

    }
    
}
