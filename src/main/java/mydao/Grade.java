package mydao;

import lombok.Data;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-18
 */
@TableName("grade")
@Data
public class Grade {
    @TableField("student_id")
    private String studentId;
    @TableField
    private String name;
}
