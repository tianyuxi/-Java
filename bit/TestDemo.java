package bit;

import java.util.Scanner;

//public class TestDemo {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//
//        int count = 0;
//        for (int i = 0;i < 32;i++) {
//            if(((n>>i)&1)==1) {
//                count++;
//            }
//        }
//        System.out.println(count);
//    }
//}
//public class TestDemo {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int count = 0;
//        while(n!=0) {
//            n = n&(n-1);
//            count++;
//        }
//        System.out.println(count);
//    }
//}

//public class TestDemo {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int i = 0;
//        for(i = 31;i >= 1;i-=2) {
//            System.out.print(((n>>i)&1) + " ");
//        }
//        for(i = 30;i >=0;i-=2) {
//            System.out.print(((n>>i)&1) + " ");
//        }
//    }
//}


//public class TestDemo{
//    public static int maxNum(int num1,int num2) {//形式参数
//        return Math.max(num1,num2);
//    }
//    public static int threeNum(int num1,int num2,int num3) {
//        return Math.max(maxNum(num1,num2),num3);
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n1 = scanner.nextInt();
//        int n2 = scanner.nextInt();
//        int n3 = scanner.nextInt();
//        int ret = threeNum(n1,n2,n3);//实参
//        System.out.println(ret);
//    }
//}

public class TestDemo {
    public static int fac(int num) {
        int ret = 1;
        for(int i = 1;i <=num;i++) {
            ret = ret * i;
        }
        return ret;
    }
    public static int factor(int n) {
        int sum = 0;
        for(int i = 1;i <= n;i++) {
            sum+=fac(i);
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(factor(n));
        scanner.close();

    }
}