package oracle;


import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/19 14:30
 * @Version: 1.0
 */
public interface TypeHandler<T> {
    /**
     * 返回结果处理
     * @param obj
     * @return
     */
    T getResult(Object obj);

    /**
     * 参数处理
     * @param ps
     * @param i
     * @throws SQLException
     */
    void setParameter(PreparedStatement ps, int i, T parameter) throws SQLException;
}
