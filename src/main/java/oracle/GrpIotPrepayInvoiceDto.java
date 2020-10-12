package oracle;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/7 18:56
 * @Version: 1.0
 */
@Setter
@Getter
public class GrpIotPrepayInvoiceDto  {
    private Long invoiceId;
    private Long oldInvoiceId;
    private String informBack;
    private String stdResultMsg;
    private String crmFailDesc;

}
