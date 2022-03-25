package bit;



//public class TestDemo3 {
//    public static void main(String[] args) {
//        int[][] array = {{1,2,3},{4,5,6}};
//        int[][] array1 = new int[][]{{1,2,3},{4,5,6}};
//        int[][] array2 = new int[2][3];
//
//        for (int i = 0; i < array.length; i++) {//遍历二维数组
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j]+" ");
//            }
//        }
//        System.out.println(Arrays.deepToString(array));//打印二维数组
//
//        for (int[]tmp:array) {
//            for (int x:tmp) {
//                System.out.print(x+" ");
//            }
//        }
//
//        int[][] array3= new int[2][];
//        array3[0] = new int[3];
//        array3[1] = new int[2];
//        for (int i = 0; i < array3.length; i++) {
//            for (int j = 0; j < array3[i].length; j++) {
//                System.out.print(array3[i][j]+" ");
//            }
//            System.out.println();
//        }
//    }
//}
class Person {//创建一个类，定义在方法外边，类的里边，一个类由字段field和方法method组成，可有可无
    //name和age为字段，也把他叫做成员变量
    //实例成员变量,实例成员没有初始化，默认值为对应的0值,引用类型默认为null;简单类型默认为0;
    public String name = "Mr.tian";
    public int age = 18;
    public char ch;
    public boolean flg;
    //静态成员变量,不属于对象
    public static int size = 10;

    public void eat() {//方法->行为
        System.out.println("eat()");
    }
    public void sleep() {
        System.out.println("sleep()");
    }
    public void show() {
        System.out.println("我叫"+this.name+"今年"+this.age+"岁");
    }
}

class Student {
    private String MyName;//限定你只能在类当中进行访问
    public int age;
    //alt+insert



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override//注解:这个注解指的是是  这个方法是重新写的
    public String toString() {
        return "Student{" +
                "MyName='" + MyName + '\'' +
                ", age=" + age +
                '}';
    }
        public String getMyName() {//提供一个公开的接口
        return this.MyName;
    }

    public void setMyName(String MyName) {
        this.MyName = MyName;//this代表这个对象的引用
    }
}

class Shape {
    public void draw(){
    }
}

class Cycle extends Shape {
    @Override
    public void draw() {
        System.out.println("画一个⚪");
    }
}

class React extends Shape {
    @Override
    public void draw() {
        System.out.println("画一个矩形");
    }
}
class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("画一个三角形");
    }
}
abstract class Shape2 {
    public abstract void draw();//抽象类主要是用来被继承的
}
class Circle extends Shape2 {
    @Override
    public void draw() {

    }
}
public class TestDemo3 {
    
    public static void drawShapes() {
        React rect = new React();
        Cycle cycle = new Cycle();
        Triangle triangle = new Triangle();
        String[] shapes = {"cycle", "react", "cycle", "rect", "Triangle"};

        for (String shape : shapes) {
            if (shape.equals("cycle")) {
                cycle.draw();
            } else if (shape.equals("rect")) {
                rect.draw();
            } else if (shape.equals("flower")) {
                triangle.draw();
            }
        }
    }
    public static void drawShapes2() {
        Shape[] shapes = {new Cycle(),new React(),new Triangle()};//向上转型
        for (Shape shape:shapes) {
            shape.draw();
        }
    }


    public static void drawMap(Shape shape) {
        shape.draw();
    }
    public static void main(String[] args){
        @SuppressWarnings({"all"})
        Person person = new Person();//实例化一个对象,通过关键字new
        System.out.println(person.name);//对象的引用.静态成员
        System.out.println(person.age);
        System.out.println(Person.size);//类名.静态成员变量
        person.eat();
        person.sleep();
        person.show();


        Student student = new Student();
        student.setMyName("Mr.tian");
        String str = student.getMyName();
        System.out.println(str);

        System.out.println(student);

        Shape shape1 = new Cycle();
        Shape shape2 = new React();
        drawMap(shape1);
        drawMap(shape2);
        @SuppressWarnings({"all"})

        Shape shape3 = new Triangle();
    }
}