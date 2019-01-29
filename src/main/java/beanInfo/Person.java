package beanInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: yyl
 * @Date: 2019/1/10 11:00
 */
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private Date birthday= new Date();
}
