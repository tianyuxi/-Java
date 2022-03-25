package bit;

import java.util.*;


public class TestDemo5 {

    public static void func (String str,char[] array){
        str = "abcdef";
        array[0] = 'g';
    }


    public static void main(String[] args) {
        String str = "holle";
        char[] val = {'a'};
        System.out.println(str);
        System.out.println(Arrays.toString(val));
        func(str,val);
        System.out.println(str);
        System.out.println(Arrays.toString(val));

        String str1 = "Holle";
        String str2 = new String("Holle").intern();//手动入池
        System.out.println(str1 == str2);
    }
    
}
