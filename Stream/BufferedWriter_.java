package Stream;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class BufferedWriter_ {
    public static void main(String[] args) throws Exception{
        String filePath = "D:\\code\\idea\\src\\Stream\\File\\new2.txt";
        //创建BufferedWriter
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath,true));
        bufferedWriter.write("Holle 田宇希");
        bufferedWriter.newLine();
        bufferedWriter.write("Holle 浴血黑帮");
        bufferedWriter.write("Holle 英雄联盟");

        bufferedWriter.close();

    }
}
