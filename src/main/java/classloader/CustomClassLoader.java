package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义ClassLoader：
 * 前面介绍的三种类加载器BootstrapClassLoader、ExtClassLoader、AppClassLoader都是加载指定的目录的jar包或者class文件
 * 那么如果需要动态去加载一些东西呢？比如从D盘某个文件夹加载一个class，或者从网络上下载class文件进行加载
 * 自定义ClassLoader就是解决这个问题的
 * 需要注意的是一个ClassLoader在创建的时候没有指定parent，那么他的parent父加载器默认就是AppClassLoader
 * 因为这样就能保证它能访问系统内置的加载器（通过getParent获取父类加载器，可以在ExtClassLoader、BootstrapClassLoader
 * 中查找）加载成功的class文件
 * @Author: yyl
 * @Date: 2018/9/21 17:43
 */
public class CustomClassLoader extends ClassLoader{
    private String mLibPath;
    public CustomClassLoader(String mLibPath){
        this.mLibPath = mLibPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(fileName);
        try{
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            while((len = fis.read())!=-1){
                bos.write(len);
            }
            byte[] data = bos.toByteArray();
            fis.close();
            bos.close();
            /**defineClass是在编写自定义的classLoader时特别重要，它能将class的二进制内容转化为Class对象
             * 如果不符合要求，就会抛出各种异常*/
            return defineClass(name,data,0,data.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * 获取需要加载的class名
     * @param name
     * @return
     */
    private String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if(index==-1){
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }
}
