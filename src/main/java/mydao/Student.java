package mydao;

import lombok.Data;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-18
 */
@TableName("student")
@Data
public class Student {
    @TableField
    private Long id;
    @TableField("cnname")
    private String name;
    @TableField
    private String sex;
    @TableField
    private String note;
}
