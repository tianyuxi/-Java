package bit;

import java.util.Scanner;

public class TestDemo10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String str = scanner.nextLine();
            StringBuffer stringBuffer = new StringBuffer();
            for(int i = 0;i < str.length();i++) {
                char ch = str.charAt(i);
                String tmp = stringBuffer.toString();
                if(!tmp.contains(ch+"")) {
                    stringBuffer.append(ch);
                }
            }
            System.out.println(stringBuffer);
        }
        scanner.close();
    }
}
