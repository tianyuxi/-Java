package internet.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws IOException {
        
        DatagramSocket datagramSocket = new DatagramSocket(9998);//构造DatagramSocket对象,准备在9998端口接收数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的问题");
        String question = scanner.next();
        byte[] data = question.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length,InetAddress.getByName("192.168.1.79"),8888);
        datagramSocket.send(datagramPacket);

        //接收回复
        byte[] reply = new byte[1024];
        datagramPacket = new DatagramPacket(reply, reply.length);
        System.out.println("等待回复");
        datagramSocket.receive(datagramPacket);

        int length = datagramPacket.getLength();
        byte[] buf = datagramPacket.getData();
        String s = new String(buf,0,length);
        System.out.println(s);

     
        datagramSocket.close();
        System.out.println("Sender端退出");

     }
}
