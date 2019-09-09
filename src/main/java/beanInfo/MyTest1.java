package beanInfo;

/**
 * BeanInfo的使用，这个应该是类似mybatis的MetaObject
 * @Author: yyl
 * @Date: 2019/1/10 10:58
 */

import java.beans.*;
import java.util.Date;

public class MyTest1 {
    public static void main(String[] args) throws Exception{
        Person person = new Person("yyl",29,new Date());
        BeanInfo beanInfo = Introspector.getBeanInfo(person.getClass());
       PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        System.out.println(propertyDescriptors.length);
       //遍历属性描述器
       for(PropertyDescriptor property:propertyDescriptors){
           System.out.println(property instanceof IndexedPropertyDescriptor);
           //修改属性名为 name的值
           if(property.getName().equals("name")){
               System.out.println("raw JavaBean name:"+property.getReadMethod().invoke(person));
               property.getWriteMethod().invoke(person,"yylhhahahah");
               System.out.println("after write JavaBean name:"+property.getReadMethod().invoke(person));
           }
       }
       MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
       for(MethodDescriptor methodDescriptor:methodDescriptors){

       }
    }
    
}
