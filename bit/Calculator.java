package bit;



class Calc {
    private int num1;
    private int num2;

    public int getNum1() {
        return this.num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return this.num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int add() {//加
        return this.num1+this.num2;
    }
    public int Subtraction() {//减法
        return this.num1-this.num2;
    }
    public int Multiplication() {//乘法
        return this.num1*this.num2;
    }
    public double Divide() {//除法
        return this.num1*1.0/this.num2;
    }
}
public class Calculator {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Calc calc = new Calc();
        calc.setNum1(10);
        calc.setNum2(20);
        System.out.println("加法");
        System.out.println(calc.add());


    }


}
