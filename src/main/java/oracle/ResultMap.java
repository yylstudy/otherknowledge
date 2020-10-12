package oracle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/19 16:30
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class ResultMap {
    /**
     * 映射集合
     */
    private List<ResultMapping> resultMappings;
    /**
     * 返回结果的JavaBean
     */
    private Class targetClass;
}
