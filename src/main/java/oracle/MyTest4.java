//package oracle;
//
//import java.sql.*;
//
///**
// * @Author: yyl
// * @Date: 2019/3/19 17:18
// */
//public class MyTest4 {
//    public static void main(String[] args) throws Exception{
//        testProcNoOut();
//    }
//
//    public static void testProcNoOut() throws Exception {
//        System.out.println("-------  start 测试调用存储过程：无返回值");
//        Connection conn = null;
//        CallableStatement callStmt = null;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.21:1521/orcl", "college_20190305", "bs");
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select fsignature from une_cbill ");
//            while (resultSet.next()){
//                Blob blob = resultSet.getBlob(1);
//                String blobString = new String(blob.getBytes(1, (int) blob.length()));
//                System.out.println(blobString);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        } finally {
//            if (null != callStmt) {
//                callStmt.close();
//            }
//            if (null != conn) {
//                conn.close();
//            }
//        }
//    }
//}
