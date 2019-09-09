package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author yang.yonglian
 * @ClassName: socket
 * @Description: TODO(这里描述)
 * @Date 2019/6/3 0003
 */
public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(8888);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = in.readLine();
        System.out.println("receive msg:"+s);
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println("test");
        String ss = in.readLine();
        System.out.println(" second receive msg:"+ss);
    }
}
