package Stream;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedReader_ {
    public static void main(String[] args) throws Exception{
        String filePath = "D:\\code\\idea\\src\\Stream\\File\\new1.txt";
        //创建bufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;//按行读取
        //bufferedReader.readLine()是按行读取文件
        //当返回null时,表示文件读取完毕
        while((line = bufferedReader.readLine())!=null) {
            System.out.println(line);
        }
        bufferedReader.close();//关闭流
    }
}
