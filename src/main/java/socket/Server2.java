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
public class Server2 {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(8888);
        int i = 0;
        for(;;) {
            Socket socket = server.accept();
            new ServerThread(socket,i).start();
            i++;
        }
    }

    static class ServerThread extends Thread{
        private Socket socket;
        public ServerThread(Socket socket,int i) throws Exception {
            super(i+"");
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s = in.readLine();
                System.out.println("recieve msg from client :"+s);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println("heihei");
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
