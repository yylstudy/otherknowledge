package switchs;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description switch case 使用
 * @createTime 2020-09-11
 */
public class MyTest1 {
    /**
     * switch case一旦case匹配，就会顺序执行后面的程序代码，而不管后面的case是否匹配，
     * 直到遇见break
     * @param args
     */
    public static void main(String[] args) {
        int num = 2;
        switch (num) {
            case 1:
                ++num;
            case 2:
                ++num;
            case 3:
                num+=2;
            default:
                num+=3;
                break;
        }
        System.out.println(num);
    }
}
