package internet;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);

        InetAddress host = InetAddress.getByName("time-a.nist.gov");
        System.out.println(host);

        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println(host2);

        String baidu = host2.getHostAddress();
        System.out.println(baidu);


    }
}
