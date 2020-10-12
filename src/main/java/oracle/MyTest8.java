package oracle;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/2/11 9:59
 * @Version: 1.0
 */
public class MyTest8 {
    public static void main(String[] args) throws Exception{
        MyTest8 myTest8 = new MyTest8();
        GrpIotPrepayInvoiceDto dto = new GrpIotPrepayInvoiceDto();
        dto.setInvoiceId(1L);
        myTest8.addForNoTransaction(dto);
    }
    public Connection getConnection() throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@10.45.44.110:1521/ora122", "gs_acct", "gs_acct");

    }
    /**
     * 属性名集合
     * @return
     */
    protected List<String> getPropertys(){
        return getResultMappings().stream()
                .map(ResultMapping::getProperty).collect(Collectors.toList());
    }
    protected String getQueryTableName() throws Exception{
        return "GRP_IOT_PREPAY_INVOCIE";
    }

    /**
     * 列名字符串
     * @return
     */
    protected String getAllColumns(){
        return getResultMappings().stream().map(ResultMapping::getColumnName).collect(Collectors.joining(","));
    }

    private int addForNoTransaction(Object param) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String placeHolder = getPropertys().stream()
                    .map(str->"?").collect(Collectors.joining(","));
            StringBuilder sb = new StringBuilder("insert into ")
                    .append(getQueryTableName())
                    .append("(")
                    .append(getAllColumns())
                    .append(") values(").append(placeHolder).append(")");
            Connection conn = getConnection();
            ps = conn.prepareStatement(sb.toString());
            setPsValue(ps,param,null);
            if(param instanceof Collection ||param.getClass().isArray()){
                int[] ss = ps.executeBatch();
                return Arrays.stream(ss).sum();
            }else{
                return ps.executeUpdate();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        } finally{

        }
    }

    private List<ResultMapping> getResultMappings(){
        return getResultMap().getResultMappings();
    }

    /**
     * 设置PS的值
     * @param dtos
     * @param ps
     */
    private void setPsValue(PreparedStatement ps,Object dtos,SQLExceptionBiFunction<Integer,PreparedStatement> wherePsResolver) throws Exception{
        List<ResultMapping> resultMappings = getResultMappings();
        if(resultMappings.isEmpty()){
            return ;
        }
        if(dtos instanceof Collection){
            for(Object obj:(Collection) dtos){
                setPsValue(ps,obj,resultMappings,wherePsResolver);
                ps.addBatch();
            }
        }else if(dtos.getClass().isArray()){
            for(Object obj:(Object[]) dtos){
                setPsValue(ps,obj,resultMappings,wherePsResolver);
                ps.addBatch();
            }
        }else{
            setPsValue(ps,dtos,resultMappings,wherePsResolver);
        }
    }

    private void setPsValue(PreparedStatement ps, Object dto, List<ResultMapping> resultMappings ,
                            SQLExceptionBiFunction<Integer,PreparedStatement> wherePsResolver){
        try{
            MetaObject obj = SystemMetaObject.forObject(dto);
            for(int i=0;i<resultMappings.size();i++){
                String property = resultMappings.get(i).getProperty();
                Object propertyValue = obj.getValue(property);
                Class propertyClass = obj.getGetterType(property);
                Class<? extends TypeHandler> typeHandlerClass = resultMappings.get(i).getTypeHandlerClass();
                if(typeHandlerClass!=null){
                    TypeHandler typeHandler = typeHandlerClass.newInstance();
                    typeHandler.setParameter(ps,i+1,propertyValue);
                    continue;
                }
                if(Date.class.isAssignableFrom(propertyClass)){
//                    PreparedStatementHelper.setParam(i+1, (Date)propertyValue, ps,"Oracle");
                }else{
                    Method method = ReflectionUtils.findMethod(PreparedStatementHelper.class,"setParam"
                            ,int.class,propertyClass,PreparedStatement.class);
                    method.invoke(null,i+1,propertyValue,ps);
                }
            }
            if(wherePsResolver!=null){
                wherePsResolver.accept(resultMappings.size()+1,ps);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected ResultMap getResultMap(){
        List<ResultMapping> list = Arrays.asList(
                new ResultMapping("INVOICE_ID","invoiceId"),
                new ResultMapping("OLD_INVOICE_ID","oldInvoiceId")
        );
        return new ResultMap(list,GrpIotPrepayInvoiceDto.class);
    }
}
