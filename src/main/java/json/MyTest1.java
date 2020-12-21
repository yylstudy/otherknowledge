package json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description jackson测试
 * @createTime 2020-11-11
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        String str1 = "{\"user_name\":\"yyl\",\"password\":\"123\",\"age\":\"30\"," +
                "\"birthday\":\"1991-12-03 11:13:11\"}";
        User user = JsonUtil.toJavaBean(str1,User.class);
        System.out.println(user);
        String listStr = "["+str1+"]";
        List<User> list = JsonUtil.toList(listStr,User.class);
        System.out.println(list);
        User[] users = JsonUtil.toArray(listStr,User.class);
        Arrays.stream(users).forEach(System.out::println);
        Map<String,Object> map = JsonUtil.toJavaBean(str1, Map.class);
        System.out.println(map);
        Map<String,Object> map1 = JsonUtil.toMap(user);
        System.out.println(map1);
    }

    @Getter
    @Setter
    @ToString
    @JsonIgnoreProperties
    static class User{
        /**
         * 序列化的属性名
         */
        @JsonProperty("user_name")
        private String username;
        @JsonIgnore
        private String password;
        private Integer age;
//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date birthday;
    }
}
