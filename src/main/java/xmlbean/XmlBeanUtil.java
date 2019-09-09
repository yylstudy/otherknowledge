package xmlbean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author yang.yonglian
 * @ClassName: xmlbean
 * @Description: TODO(这里描述)
 * @Date 2019/7/19 0019
 */
public class XmlBeanUtil {
    public static void main(String[] args){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<student>" +
                "<id>sss</id>" +
                "<Name>yyl</Name>" +
                "<age>23</age>" +
                "<coursers>" +
                "<courser>" +
                "<name>test1</name>" +
                "</courser>" +
                "<courser>" +
                "<name>test2</name>" +
                "</courser>" +
                "</coursers>" +
                "</student>"
                ;
        Student student = xmlToBean(xml,Student.class);
        System.out.println(student);
//        Student student = new Student();
//        student.setAge("21");
//        student.setName("yyl");
//        List<Courser> list = new ArrayList<>();
//        Courser courser = new Courser("test1");
//        Courser courser2 = new Courser("test2");
//        list.add(courser);
//        list.add(courser2);
//        student.setCoursers(list);
//        String str = beanToXml(student);
//        System.out.println(str);
    }

    /**
     * bean转xml,不支持非静态内部类，因为非static类在调用getDeclaredFields时，存在
     * this$0 属性
     * @param obj
     * @return
     */
    public static String beanToXml(Object obj){
        XStream xStream = new XStream();
        Set<Class> set = new HashSet();
        set.add(obj.getClass());
        getAllClass(obj.getClass(),set);
        xStream.processAnnotations(set.toArray(new Class[set.size()]));
        return xStream.toXML(obj);
    }

    /**
     * xml转bean,不支持非静态内部类，因为非static类在调用getDeclaredFields时，存在
     * this$0 属性
     * @param xml
     * @param targetClass 目标class
     * @param <T>
     * @return
     */
    public static <T> T xmlToBean(String xml,Class<T> targetClass){
        if(StringUtils.isEmpty(xml)){
            throw new RuntimeException("xml is null");
        }
        XStream xStream = new XStream();
        Set<Class> set = new HashSet();
        set.add(targetClass);
        getAllClass(targetClass,set);
        xStream.processAnnotations(set.toArray(new Class[set.size()]));
        T t = (T)xStream.fromXML(xml);
        return t;
    }
    private static void getAllClass(Class clazz,Set<Class> set){
        do {
            for (Field field : clazz.getDeclaredFields()) {
                if(!isPrimaryType(field.getType())){
                    set.add(clazz);
                    getAllClass(field.getType(),set);
                }
            }
            clazz = clazz.getSuperclass();
        }while (clazz != null && clazz != Object.class);
    }

    private static boolean isPrimaryType(Class fieldClass){
        if(fieldClass.isPrimitive()||fieldClass==String.class
                ||fieldClass==Integer.class
                ||fieldClass==Double.class
                ||fieldClass==Long.class
                ||fieldClass==Character.class
                ||fieldClass==Boolean.class
                ||fieldClass==Float.class
                ||fieldClass==Byte.class
                ||fieldClass==String.class
                ||fieldClass==Short.class){
            return true;
        }
        return false;
    }
    @Getter
    @Setter
    static class Animal{
        private String id;
    }

    @Getter
    @Setter
    @ToString
    @XStreamAlias("student")
    static class Student extends Animal{
        private String Name;
        private String age;
        private List<Courser> coursers;
    }
    @Getter
    @Setter
    @ToString
    @XStreamAlias("courser")
    @AllArgsConstructor
    static class Courser{
        private String name;
    }
}
