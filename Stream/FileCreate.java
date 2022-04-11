package Stream;

import java.io.File;
import java.io.IOException;

public class FileCreate {
    public static void create01() {
        String filePath = "D:\\code\\idea\\src\\Stream\\File\\new1.txt";
        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件创建成功");
    }

    public static void create02() {
        File parentFile = new File("D:\\code\\idea\\src\\Stream\\File");
        String fileName = "new2.txt";
        //这里的file对象,在Java程序中,只是一个对象
        //只有执行了creaNewFile方法,才能真正的在磁盘创建该文件
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("第二种创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void create03() {
        String parentPath = "D:\\code\\idea\\src\\Stream\\File";
        String filePath = "news3.txt";
        File file = new File(parentPath, filePath);

        try {
            file.createNewFile();
            System.out.println("第三种创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File file = new File("D:\\code\\idea\\src\\Stream\\File\\new1.txt");

        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());


    }
}
