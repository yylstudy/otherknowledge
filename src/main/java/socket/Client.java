package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author yang.yonglian
 * @ClassName: socket
 * @Description: TODO(这里描述)
 * @Date 2019/6/3 0003
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println("hello socket");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = in.readLine();
        System.out.println("receive msg from server:"+s);
        out.println("hei hei");
    }
}
