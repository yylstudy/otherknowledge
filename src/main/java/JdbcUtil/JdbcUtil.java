package JdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/11/28 11:30
 * @Version: 1.0
 */
public class JdbcUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static{
        try {
            url = "jdbc:mysql://localhost:3306/mybatis";
            user = "root";
            password = "root";
            driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (Exception e) {

            e.getMessage();
        }

    }

    public static void main(String[] args) throws Exception{
        CompletableFuture[] ss = IntStream.rangeClosed(0,1)
                .mapToObj(i-> CompletableFuture.supplyAsync(()->doWork())).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(ss).join();

    }

    private static Object doWork(){
        try {
            Connection connection = getConn();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement selectPs = connection.prepareStatement("select count(1) from test1 where username='yyl' ");
            System.out.println("-----------------------------");
            ResultSet rs = selectPs.executeQuery();
            long total = 0;
            if(rs.next()){
                total = rs.getLong(1);
            }
            selectPs.close();
            rs.close();
            Thread.sleep(10);
            if(total==0){
                PreparedStatement ps = connection.prepareStatement("insert test1(username,test1) values(?,?)");
                ps.setString(1,"yyl");
                ps.setString(2, "123");
                ps.executeUpdate();
                ps.close();
            }
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static Connection getConn() throws Exception{

        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,
                             Connection connection){

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if(preparedStatement != null ){
                preparedStatement.close();
            }
            if(connection != null ){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
