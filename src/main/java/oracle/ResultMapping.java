package oracle;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/19 14:29
 * @Version: 1.0
 */
@Getter
@Setter
public class ResultMapping implements Serializable {
    private String columnName;
    private String property;
    private Class<? extends TypeHandler> typeHandlerClass;

    public ResultMapping(String columnName, String property) {
        this.columnName = columnName;
        this.property = property;
    }

    public ResultMapping(String columnName, String property, Class<? extends TypeHandler> typeHandlerClass) {
        this.columnName = columnName;
        this.property = property;
        this.typeHandlerClass = typeHandlerClass;
    }
}
