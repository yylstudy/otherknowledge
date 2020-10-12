package mydao;

import JdbcUtil.JdbcUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-18
 */
public  class BaseDao<T> {
    @Getter
    @Setter
    @ToString
    static class FieldInfo{
        private String column;
        private Field field;
    }
    @Getter
    @Setter
    @ToString
    static class TableInfo{
        private String tableName;
        private List<FieldInfo> fieldInfos;
        private String allColumn;
        private Class resultType;
    }
    private static Map<Class,TableInfo> tableInfoMap = new ConcurrentHashMap<>();

    protected  List<T> query(String whereCondition, Consumer<PreparedStatement> consumer)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            Class<T> clazz = findClass();
            TableInfo tableInfo = getTableInfo(clazz);
            String sqlStr = "select " +tableInfo.getAllColumn()+" from "+ tableInfo.getTableName();
            if(StringUtils.isNotBlank(whereCondition)){
                sqlStr+=" "+whereCondition;
            }
            connection = JdbcUtil.getConn();
            ps = connection.prepareStatement(sqlStr);
            rs = doQuery(ps,consumer);
            return handleResult(rs,tableInfo);
        }catch (Exception e){
            throw new RuntimeException(e);
        } finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
    }
    private TableInfo getTableInfo(Class<T> clazz){
        if(!tableInfoMap.containsKey(clazz)){
            synchronized (tableInfoMap){
                if(!tableInfoMap.containsKey(clazz)){
                    System.out.println("getTableInfo:"+clazz.getName());
                    TableInfo tableInfo = new TableInfo();
                    List<String> fieldNames = new ArrayList<>();
                    List<FieldInfo> fieldInfos = new ArrayList<>();
                    TableName tableName = clazz.getAnnotation(TableName.class);
                    tableInfo.setTableName(tableName.value());
                    Class currentClazz = clazz;
                    while(currentClazz!=Object.class){
                        Field[] fields = clazz.getDeclaredFields();
                        for(Field field:fields){
                            //存在属性名相同的默认取子类的
                            if(!fieldNames.contains(field.getName())&&field.isAnnotationPresent(TableField.class)){
                                TableField tableField = field.getAnnotation(TableField.class);
                                FieldInfo fieldInfo = new FieldInfo();
                                String column = tableField.value();
                                if(StringUtils.isEmpty(column)){
                                    column = field.getName();
                                }
                                fieldInfo.setColumn(column);
                                fieldInfo.setField(field);
                                fieldInfos.add(fieldInfo);
                            }
                        }
                        currentClazz = currentClazz.getSuperclass();
                    }
                    tableInfo.setFieldInfos(fieldInfos);
                    String allColumn = fieldInfos.stream().map(FieldInfo::getColumn).collect(Collectors.joining(","));
                    tableInfo.setAllColumn(allColumn);
                    tableInfo.setResultType(clazz);
                    tableInfoMap.put(clazz,tableInfo);
                }
            }
        }
        return tableInfoMap.get(clazz);
    }

    private ResultSet doQuery(PreparedStatement ps,
                              Consumer<PreparedStatement> consumer) throws SQLException{
        if(consumer!=null){
            consumer.accept(ps);
        }
        return ps.executeQuery();
    }

    private  List<T> handleResult(ResultSet rs, TableInfo tableInfo) throws Exception{
        List<T> list = new ArrayList<>();
        while (rs.next()){
            T resultInstance = (T)tableInfo.getResultType().newInstance();
            List<FieldInfo> fieldInfos = tableInfo.getFieldInfos();
            for(int i=0;i<fieldInfos.size();i++){
                FieldInfo fieldInfo = fieldInfos.get(i);
                Field field = fieldInfo.getField();
                Method method = rs.getClass().getMethod("get"+field.getType().getSimpleName(),int.class);
                method.setAccessible(true);
                Object propertyValue = method.invoke(rs,i+1);
                if(propertyValue!=null){
                    field.setAccessible(true);
                    field.set(resultInstance,propertyValue);
                }

            }
            list.add(resultInstance);
        }
        return list;
    }

    private Class<T> findClass(){
        Class search = this.getClass();
        Type genericSuperclass = search.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if(parameterizedType.getActualTypeArguments().length!=1){
                throw new RuntimeException(this.getClass()+" getGenericSuperclass length is not 1");
            }
            Type targetClass = parameterizedType.getActualTypeArguments()[0];
            if(targetClass instanceof Class){
                return (Class)targetClass;
            }
        }
        return null;
    }

    public static Map<Class, TableInfo> getTableInfoMap() {
        return tableInfoMap;
    }
}
