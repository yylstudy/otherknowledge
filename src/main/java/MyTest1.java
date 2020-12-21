import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-28
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        String ss = "大家前进的xxxxxxxxxxxxxxxx情况的科技空气经得起篮球教练继电器科技电击文库我看见我打开外壳的健康";
        System.out.println(ss);
        ss = new String(ss.getBytes(), Charset.forName("gbk"));
        System.out.println(ss);
    }
}
