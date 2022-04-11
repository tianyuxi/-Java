package Stream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Properties01 {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("D:\\code\\idea\\src\\Stream\\File\\my.properties"));
        // String line = "";
        // while((line = br.readLine()) != null) {
        //     String[] split = line.split("=");
        //     System.out.println(split[0]+"值是:"+split[1]); 
        // }
        // br.close();


        Properties properties = new Properties();
        properties.load(new FileReader("D:\\code\\idea\\src\\Stream\\File\\my.properties"));
        properties.list(System.out);

        String user = properties.getProperty("user");
        System.out.println(user);

        String pwd = properties.getProperty("pwd");
        System.out.println(pwd);

        properties.setProperty("chaset", "utf8");
        properties.setProperty("user", "Tommy");
        properties.setProperty("pwd", "abc111");
        properties.store(new FileOutputStream("D:\\code\\idea\\src\\Stream\\File\\my.properties"), null);
        System.out.println("保存成功");

    }
}
