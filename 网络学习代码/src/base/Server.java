package base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//服务端
public class Server {

    public static int PORT=9999;//为了客户端能更好的访问，设置的端口号

    private static ExecutorService POOL= Executors.newCachedThreadPool();//线程池

    public static void main(String[] args) throws IOException {
        //cmd中输入：netstat -ano|findstr "9999" 可以显示进程的pid（通过pid可以查找进程）
        //启动了服务端程序
        ServerSocket server=new ServerSocket(PORT);//绑定一个端口号
        while (true) {
            Socket client= server.accept();//阻塞等待客户端连接，有新的链接来，就往下执行
            POOL.submit(new ServerTask(client));
        }
    }
    private static class ServerTask implements Runnable{
        private Socket client;

        public ServerTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            //处理client 中输入输出流，也就是发送/读取网络数据包
            try {
                InputStream is=client.getInputStream();
                InputStreamReader isr=new InputStreamReader(is,"UTF-8");
                BufferedReader br=new BufferedReader(isr);
                OutputStream os=client.getOutputStream();
                PrintWriter pw=new PrintWriter(os,true);

                String line;
                while ((line=br.readLine())!=null){
                    System.out.println("接受客户端数据"+line);
                    pw.println("响应"+line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
