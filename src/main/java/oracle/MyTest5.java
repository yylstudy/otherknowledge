//package oracle;
//
//import java.io.Writer;
//import java.sql.*;
//
///**
// * @Author: yyl
// * @Date: 2019/3/19 17:18
// */
//public class MyTest5 {
//    public static void main(String[] args) throws Exception{
//        testProcNoOut();
//    }
//
//    public static void testProcNoOut() throws Exception {
//        Connection conn = null;
//        CallableStatement callStmt = null;
//        try {
//            long t1 = System.currentTimeMillis();
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.21:1521/orcl", "college_20190305", "bs");
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select fsignature from une_cbill");
//            String blobString = "";
//            if (resultSet.next()){
//                Blob blob = resultSet.getBlob(1);
//                blobString = new String(blob.getBytes(1, (int) blob.length()));
//            }
//            long t2 = System.currentTimeMillis();
//            System.out.println("取数耗时："+(t2-t1));
//            oracle.sql.CLOB clob = oracle.sql.CLOB.createTemporary(
//                    conn, false,
//                    oracle.sql.CLOB.DURATION_SESSION);
//            clob.open(oracle.sql.CLOB.MODE_READWRITE);
//            Writer writer = clob.getCharacterOutputStream();
//            StringBuffer sb = new StringBuffer();
//            sb.append("'1'");
//            writer.write(sb.toString());
//            writer.flush();
//            writer.close();
//            callStmt = conn.prepareCall("{call pro_sync_update(?,?)}");
//            callStmt.registerOutParameter(2,Types.VARCHAR);
//            callStmt.setClob(1, clob);
//
//            callStmt.execute();
//            String result = callStmt.getString(2);
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        } finally {
//            if (null != conn) {
//                conn.close();
//            }
//        }
//    }
//
//
//    public static String hexStringToString(String s) {
//        if (s == null || s.equals("")) {
//            return null;
//        }
//        s = s.replace(" ", "");
//        byte[] baKeyword = new byte[s.length() / 2];
//        for (int i = 0; i < baKeyword.length; i++) {
//            try {
//                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            s = new String(baKeyword, "UTF-8");
//            new String();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return s;
//    }
//}
