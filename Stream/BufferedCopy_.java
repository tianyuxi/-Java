package Stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedCopy_ {
    public static void main(String[] args) {
        String srcFilePath = "D:\\code\\idea\\src\\Stream\\File\\new2.txt";
        String destFilePath = "D:\\code\\idea\\src\\Stream\\File\\news3.txt";
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(srcFilePath));
            bw = new BufferedWriter(new FileWriter(destFilePath));

            while((line = br.readLine())!=null) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("拷贝完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
                if(bw !=null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
