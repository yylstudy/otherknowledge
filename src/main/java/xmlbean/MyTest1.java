package xmlbean;

/**
 * @Author yang.yonglian
 * @ClassName: xmlbean
 * @Description: TODO(这里描述)
 * @Date 2019/7/19 0019
 */
public class MyTest1 {
    public static void main(String[] args){
        String xml ="<ContractRoot>\n" +
                "\t<TcpCont>\n" +
                "\t\t<TransactionID>1000000207201112041000000011</TransactionID>\n" +
                "\t\t<ActionCode>1</ActionCode>\n" +
                "\t\t<RspTime>20120109000009</RspTime>\n" +
                "\t\t<Response>\n" +
                "\t\t\t<RspType>0</RspType>\n" +
                "\t\t\t<RspCode>0000</RspCode>\n" +
                "\t\t</Response>\n" +
                "\t</TcpCont>\n" +
                "\t<SvcCont>\n" +
                "\t\t{\n" +
                "\t\t\"resultCode\": 0,\n" +
                "\t\t\"message\": \"ok\",\n" +
                "\t\t\"data\": [\n" +
                "\t\t{\n" +
                "\t\t\"msisdn\": \"14919404149\",\n" +
                "\t\t\"start_time\": \"2017/09/04 09:09:12\",\n" +
                "\t\t\"biz_type\": \"国内\",\n" +
                "\t\t\"call_type\": \"主叫\",\n" +
                "\t\t\"other_party\": \"15805839813\",\n" +
                "\t\t\"prov\": “0”,\n" +
                "\t\t\"long_type\": \"国内\",\n" +
                "\t\t\"duration\": \"1分\",     //单条详单采取进一法，不足1分钟补足1分钟      \n" +
                "\t\t\"group_billing\": \"0.0\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\"msisdn\": \"14919404149\",\n" +
                "\t\t\"start_time\": \"2017/09/04 10:09:17\",\n" +
                "\t\t\"biz_type\": \"国内\",\n" +
                "\t\t\"call_type\": \"主叫\",\n" +
                "\t\t\"other_party\": \"15805839813\",\n" +
                "\t\t\"prov\": \"0\",\n" +
                "\t\t\"long_type\": \"国内\",\n" +
                "\t\t\"duration\": \"1分\",      //单条详单采取进一法，不足1分钟补足1分钟\n" +
                "\t\t\"group_billing\": \"0.0\"\n" +
                "\t\t}\n" +
                "\t\t] ,\n" +
                "\t\t\"sum\":{\n" +
                "\t\t\"fee\":0.00,    //总费用（单位元）保留2位有效数字\n" +
                "\t\t\"call_duration\": 2   //总时长（单位分钟）单条详单采取进一法，不足1分钟补足1分钟\n" +
                "\t\t}\n" +
                "\t</SvcCont>\n" +
                "</ContractRoot>\n";
        ContractRoot ss = XmlBeanUtil.xmlToBean(xml,ContractRoot.class);
        System.out.println(ss);
    }
}
