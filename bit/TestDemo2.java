package bit;

//                            _ooOoo_")
//                           o8888888o")
//                           88  .  88")
//                           (| -_- |)")
//                          0\\    =  /0")
//                        ____/`---'\\____")
//                      .'  \\\\|     |//  `.")
//                     /  \\\\|||  :  |||//  \\")
//                    /  _||||| -:- |||||-  \\")
//                    |   | \\\\\\  -  /// |   |")
//                    | \\_|  ''\\---/''  |   |")
//                    \\  .-\\__  `-`  ___/-. /")
//                  ___`. .'  /--.--\\  `. . __")
//               .\"\" '<  `.___\\_<|>_/___.'  >'\"\".")
//              | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |")
//              \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /")
//         ======`-.____`-.___\\_____/___.-`____.-'======")
//                            `=---='")
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")
//                      Buddha Bless, No Bug !")

import java.util.Arrays;

public class TestDemo2 {
    public static boolean isUp(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if(array[i]<array[i+1]){
                return false;
            }
        }
        return true;
    }
    public static int BinarySearch(int[] array,int key) {//二分查找
        int right = array.length-1;
        int left = 0;
        while(left <= right) {
            int middle = (right+left)/2;
            if(array[middle]==key) {
                return middle;
            }else if(array[middle]<key){
                left=middle+1;
            }else {
                right=middle-1;
            }
        }
        return -1;
    }
    public static int find(int[] array,int key) {
        for (int i = 0; i < array.length; i++) {
            if(array[i]==key) {
                return i;
            }
        }
        return -1;//数组是没有-1下标的
    }
    public static double average(int[] array) {
        int sum = 0;
        for (int x:array) {
            sum += x;
        }//foreach求数组的和
        return (double)sum/ (double)array.length;
    }
    public static int searchMax(int[] array) {
        int max = array[0];
        for (int i = 2; i < array.length; i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] array = new int[]{12,2,18,24,76,13};
        int ret = searchMax(array);
        System.out.println(ret);

        System.out.println(average(array));

        int ret1 = find(array,24);
        System.out.println(ret1);

        int ret2 = BinarySearch(array,24);
        System.out.println(ret2);

        System.out.println(Arrays.binarySearch(array, 24));
        System.out.println(Arrays.binarySearch(array,1,4 ,24));

        int[] ret3 = Arrays.copyOfRange(array,1,5);//拷贝一个区间,左闭右开
        System.out.println(Arrays.toString(ret3));

        System.out.println(Arrays.equals(array, ret3));//判断两个数组是否相同
        int[] array1 = new int[10];
        Arrays.fill(array1,8);//给一个数组填充
        System.out.println(Arrays.toString(array1));
        int[] array2 = new int[10];
        Arrays.fill(array2,5,7,9);//局部填充

        Arrays.sort(array);//给数组排序
        System.out.println(Arrays.toString(array));

        System.out.println(isUp(array));
    }
}