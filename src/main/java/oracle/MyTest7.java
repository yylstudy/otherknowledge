package oracle;

import java.io.Writer;
import java.sql.*;

/**
 * @Author: yyl
 * @Date: 2019/3/19 17:18
 */
public class MyTest7 {
    public static void main(String[] args) throws Exception{
        testProcNoOut();
    }

    public static void testProcNoOut() throws Exception {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.21:1521/orcl", "college_20190305", "bs");
            callStmt = conn.prepareCall("{call pro_sync_add(?)}");
            oracle.sql.CLOB clob = oracle.sql.CLOB.createTemporary(
                    conn, false,
                    oracle.sql.CLOB.DURATION_SESSION);
            clob.open(oracle.sql.CLOB.MODE_READWRITE);
            Writer writer = clob.getCharacterOutputStream();
            writer.write("<?xml version=\"1.0\"  encoding=\"utf-8\"?>\n" +
                    "<request>\n" +
                    "\t<delsql>delete from test1</delsql>\n" +
                    "\t<insertsql>"+getinsert(conn)+"</insertsql>\n" +
                    "  </request>");
            writer.flush();
            writer.close();
            // 参数index从1开始，依次 1,2,3...
            callStmt.setClob(1, clob);
            callStmt.execute();

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


    public static String getinsert(Connection conn) throws Exception{
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select fsignature from une_cbill ");
        String blobString = "";
        if (resultSet.next()){
            Blob blob = resultSet.getBlob(1);
            blobString = new String(blob.getBytes(1, (int) blob.length()));
        }

        oracle.sql.CLOB clob = oracle.sql.CLOB.createTemporary(
                conn, false,
                oracle.sql.CLOB.DURATION_SESSION);
        clob.open(oracle.sql.CLOB.MODE_READWRITE);
        Writer writer = clob.getCharacterOutputStream();
        StringBuffer sb = new StringBuffer("insert all " +
                "into test1(tablename,clo) values('333','"+blobString+"')");
        for(int i=0;i<10;i++){
            sb.append("into test12(tablename,clo) values('333','"+blobString+"')");
        }
        sb.append("select 1 from dual");
        return sb.toString();
    }
}
