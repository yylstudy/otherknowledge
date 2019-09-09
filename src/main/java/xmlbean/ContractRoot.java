package xmlbean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author yang.yonglian
 * @ClassName: com.ztesoft.bss.accounting.dto.satellitemgr.common
 * @Description: TODO(这里描述)
 * @Date 2019/7/19 0019
 */
@Getter
@Setter
@XStreamAlias("ContractRoot")
@ToString
public class ContractRoot {
    private TcpCont TcpCont;
    private String SvcCont;

    @Getter
    @Setter
    @ToString
    @XStreamAlias("TcpCont")
    public static class TcpCont{
        private String TransactionID;
        private String ActionCode;
        private String RspTime;
        private Response Response;
    }
    @Getter
    @Setter
    @ToString
    @XStreamAlias("Response")
    public static class Response{
        private String RspType;
        private String RspCode;
    }

}
