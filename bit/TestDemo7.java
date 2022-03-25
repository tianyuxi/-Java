package bit;

public class TestDemo7 {
    public static String reverse(String str,int begin,int end) {//字符串逆置
        char[] value = str.toCharArray();//转化为数组
        while(begin < end) {
            char tmp = value[begin];
            value[begin] = value[end];
            value[end] = tmp;
            begin++;
            end--;
        }
        return new String(value); 
    }

    public static String func(String str, int n) {//反转字符串
        if(str == null||n <= 0||n >= str.length()) {
            return null;
        }
        str = reverse(str, 0, n-1);
        str = reverse(str, n, str.length()-1);
        str = reverse(str, 0, str.length()-1);
        return str;

    }
    public static void main(String[] args) {
        String str = "abc   def";
        boolean fla = str.contains("a");//查找
        System.out.println(fla);

        int index = str.indexOf("abc",1);
        System.out.println(index);//返回首字母下标

        index = str.lastIndexOf("abc",1);//从后往前找
        System.out.println(index);

        boolean flg = str.startsWith("a",1);//判断是否以指定字符开头
        flg = str.endsWith("f");//判断是否以指定字符结尾
        System.out.println(flg);

        String[] string = str.split(" ");//字符串拆分
        for (String s : string) {
            System.out.println(s);
        }

        String str1 = "192.168.1.1";
        String[] strings = str1.split("\\.");
        for (String string2 : strings) {
            System.out.println(string2);
        }

        String str2 = "name=zhangsan&age=18";
        String[] string1 = str2.split("&");
        for (String s1 : string1) {
            String[] string2 = s1.split("=");
            for (String s2 : string2) {
                System.out.println(s2);
            }
        }

        String str3 = str.toLowerCase();
        System.out.println(str3);
    }
}
