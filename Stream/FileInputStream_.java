package Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStream_ {
    public static void readFile01() {
        String filePath = "D:\\code\\idea\\src\\Stream\\File\\new1.txt";
        int readData = 0;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            while((readData=fileInputStream.read())!=-1) {
                System.out.print((char)readData);
            }    
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //使用read(byte[] b)读取,提高效率
    public static void readFile02() {
        String filePath = "D:\\code\\idea\\src\\Stream\\File\\new1.txt";
        byte[] buf = new byte[8];
        int readLen = 0;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            while((readLen = fileInputStream.read(buf))!=-1) {//如果返回-1,表示读取完毕,如果读取正常,放回实际读取的字节数
                System.out.print(new String(buf,0,readLen));
            }    
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeFile() {
        String filePath = "D:\\code\\idea\\src\\Stream\\File\\new1.txt";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath,true);//有true是追加,没有true是覆盖
            String str = "by order of peaky binders";
            fileOutputStream.write(str.getBytes());//把字符串转为字节数组

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // readFile01();
        // System.out.println();
        // readFile02();
        writeFile();
    }
}