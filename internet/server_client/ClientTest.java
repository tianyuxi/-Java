package internet.server_client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
    public static void main(String[] args) throws UnknownHostException, IOException {
        //连接本机的9999端口,如果连接成功,返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端socket返回="+socket.getClass());

        //outputStream.write("holle,server".getBytes());

        //OutputStreamWrute可以把outputStream这样的字节流转为字符流
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello,server字符流");
        bufferedWriter.newLine();//插入一个换行,表示写入的内容结束,要求对方使用readLine()
        bufferedWriter.flush();//使用字符流,需要手动刷新,否则数据不会写入数据通道
        //socket.shutdownOutput();
        // byte[] buf = new byte[1024];
        // int readLen = 0;
        // while((readLen = inputStream.read(buf)) != -1) {
        //     System.out.println(new String(buf,0,readLen));
        // }
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);
        
        bufferedReader.close();//关闭外层流
        bufferedWriter.close();
        socket.close();
    }
}
