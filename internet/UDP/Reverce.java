package internet.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Reverce {
    public static void main(String[] args) throws IOException {
        //先运行这个
        DatagramSocket datagramSocket = new DatagramSocket(8888);//构建一个DatagramSocket对象,在9999接收数据
        byte[] data = new byte[1024];//最大64kb
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        System.out.println("接收端等待接收数据");
        datagramSocket.receive(datagramPacket);

        int length = datagramPacket.getLength();
        byte[] buf = datagramPacket.getData();
        String s = new String(buf,0,length);
        String answer = "";
        if("四大名著是哪些".equals(s)) {
            answer = "<<水浒传>><<三国演义>><<西游记>><<红楼梦>>";
        }else {
            answer = "what";
        }
        System.out.println(answer);

        //回复消息给Male

        data = answer.getBytes();
        datagramPacket = new DatagramPacket(data, data.length,InetAddress.getByName("192.168.1.79"),9998);
        datagramSocket.send(datagramPacket);

        datagramSocket.close();
        System.out.println("Reverce端退出");
    }
}
UDP说明:
1、没有明确的服务器和客户端,演变成数据的发送端和接收端
2、接收数据和发送数据时通过DatagramSocket对象完成
3、将数据封装到DatagramPacket对象/装包
4、当接收到DatagramPacket对象,需要进行拆包,取出数据
5、DatagramSocket可以指定在哪个端口接收数据

