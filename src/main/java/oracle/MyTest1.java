package oracle;

import java.sql.*;

/**
 * @Author: yyl
 * @Date: 2019/3/19 17:18
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        testProcNoOut();
    }

    public static void testProcNoOut() throws Exception {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.21:1521/orcl", "college_20190305", "bs");
            // 存储过程 TEST_MICHAEL_NOOUT 其实是向数据库插入一条数据
            callStmt = conn.prepareCall("{call pro_sync_select(?,?)}");

            // 参数index从1开始，依次 1,2,3...
            callStmt.setString(1, "ibe_bus_bill_info@@1");
            callStmt.registerOutParameter(2, Types.CLOB);
            callStmt.execute();
            Clob clob = callStmt.getClob(2);
            String jcxmStr = clob.getSubString(1, (int)clob.length());
            System.out.println(jcxmStr);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            if (null != callStmt) {
                callStmt.close();
            }
            if (null != conn) {
                conn.close();
            }
        }
    }
}
