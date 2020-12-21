package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.ArrayType;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description jackson 工具类
 * @createTime 2020-11-11
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        //为空不输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //空对象转json不报错
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //禁止序列化日期为timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //禁止遇到未知属性抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //空字符串为null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //决定parser将是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）。
        //由于JSON标准说明书上面没有提到注释是否是合法的组成，所以这是一个非标准的特性；
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS,true);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //时间转化器
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    public static ObjectMapper getMapper(){
        return mapper;
    }

    /**
     * 对象转json字符串
     * @param o
     * @return
     */
    public static String toJsonString(Object o){
        try{
            return mapper.writeValueAsString(o);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转JavaBean
     * @param json
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T toJavaBean(String json,Class<T> targetClass){
        try{
            return mapper.readValue(json,targetClass);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转List
     * @param json
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> targetClass){
        try{
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,targetClass);
            return mapper.readValue(json,javaType);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转数组
     * @param json
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(String json, Class<T> targetClass){
        try{
            ArrayType arrayType = mapper.getTypeFactory().constructArrayType(targetClass);
            return mapper.readValue(json,arrayType);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转map
      * @param obj
     * @return
     */
    public static Map<String,Object> toMap(Object obj){
        try{
            return mapper.convertValue(obj,Map.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
