package oracle;

import java.sql.SQLException;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/15 15:29
 * @Version: 1.0
 */
@FunctionalInterface
public interface SQLExceptionBiFunction<T, U> {
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     */
    void accept(T t, U u) throws SQLException;
}
