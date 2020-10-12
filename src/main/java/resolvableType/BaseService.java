package resolvableType;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/9 11:12
 * @Version: 1.0
 */
public class BaseService<T> {
    private BaseDao<T> baseDao;
    public int insert(T t){
        return baseDao.insert(t);
    }
    public List<T> findList(){
        return baseDao.findList();
    }
}
