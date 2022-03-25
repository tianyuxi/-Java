package bit;

import java.util.Arrays;

public class TestDemo6 {
    public static boolean func(String str) {
        for(int i = 0;i < str.length();i++) {
            if(str.charAt(i) >'0' || str.charAt(i) < '9') {
                return false;
            }
        }
        return true;
        
        
    }
    public static void main(String[] args) {
        char[] val = {'a','b','c','d','e'};
        String str = new String(val,1,3);
        System.out.println(str);

        String str2 = "Holle";
        char ch = str2.charAt(0);
        System.out.println(ch);
        //把字符串转化为数组

        char[] val2 = str2.toCharArray();
        System.out.println(val2);

        System.out.println(func(str2));

        byte[] bytes = {97,98,99,100};
        String str3 = new String(bytes);//把数字转化为字符
        byte[] byte1 = str2.getBytes();//把字符转化为数字
        System.out.println(Arrays.toString(byte1));
        System.out.println(str3);

        String str4 = "holle";
        System.out.println(str2.equalsIgnoreCase(str4));//忽略大小写的比较

        System.out.println(str2.compareTo(str4));

        String str5 = str4.replace("ho", "Ho");
        //replaceAll替换
        //replaceFirst替换第一次出现的
        //substring(int,beginIndex)从指定索引截取到结尾
        //substring(int,beginIndex,int endIndex)截取部分内容
        System.out.println(str5);
        String str6 = "   abc   def    ".trim();//去掉左右空格,中间去不掉
        System.out.println(str6);
    }
}
