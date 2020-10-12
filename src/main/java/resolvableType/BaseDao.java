package resolvableType;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/17 17:02
 * @Version: 1.0
 */
public  class BaseDao<T> {
    private T t;
    private Map<String,T> tt;
    int insert(T t){
        return 0;
    }
    List<T> findList(){
        return null;
    }
}
