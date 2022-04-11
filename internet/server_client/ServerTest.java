package internet.server_client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        //在本机的9999端口监听,等待连接,在本机没有其他服务器监听9999
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器,在9999端口监听,等待连接...");
        //当在客户端连接9999端口时,程序会阻塞,等待连接
        //如果有服务器连接,则会返回Socket对象,程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务器socket="+socket.getClass());

        // byte[] buf = new byte[1024];
        // int readLen = 0;
        // while((readLen = inputStream.read(buf)) != -1) {
        //     System.out.println(new String(buf,0,readLen));
        // }

        //IO读取,使用字符流,使用InputStreamReader将inputStream转成字符流
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        // outputStream.write("Holle client".getBytes());
        // socket.shutdownOutput();

        //使用字符输出流的方式回复信息
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Holle client字符流");
        bufferedWriter.newLine();//插入换行,表示恢复内容结束
        bufferedWriter.flush();


        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        serverSocket.close();
    }
}
