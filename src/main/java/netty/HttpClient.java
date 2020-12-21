package netty;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-20
 */
public class HttpClient {
    public static void main(String[] args) throws Exception{
        String ss = HttpUtils.doGet("http://127.0.0.1:8083/ssss");
        System.out.println(ss);
    }
}
