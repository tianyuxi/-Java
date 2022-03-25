package bit;

class PersonQ implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
       
        return super.clone();
    }
}



public class TestDemo8 {
    public static int divide(int x,int y) throws ArithmeticException{//声明异常,让方法的调用者知道
        if(y == 0) {
            throw new ArithmeticException("y == 0");//除数==0,手动抛出异常
        }
        return x/y;
    }
    @SuppressWarnings({"all"})

    public static void main(String[] args) {

        //StringBuffer stringBuffer = new StringBuffer();多线程
        StringBuilder stringBuilder = new StringBuilder();//不会产生临时变量
        stringBuilder.append("Holle");
        stringBuilder.append(" World");
        String ret = stringBuilder.toString();
        System.out.println(ret);
        
        StringBuilder stringBuilder2 = stringBuilder.reverse();
        System.out.println(stringBuilder2);

        int[] array = {1,2,3};
        try {
            System.out.println(array[4]);
        }catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("捕获到了数组越界异常");
        }finally {
            System.out.println("finally一定会被执行");
        }
        System.out.println("Holle");

        try {
            int ret1 = divide(20, 0);
            System.out.println(ret1);
        }catch(ArithmeticException e) {
            e.printStackTrace();
            System.out.println("hhhh");
        }
        System.out.println("继续执行");
        

        PersonQ person = new PersonQ();
        try {
            Person person2 = (Person)person.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
