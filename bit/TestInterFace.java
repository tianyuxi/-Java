package bit;
// interface Shape {
//     public abstract void draw();

//     public int a = 10;
//     public static final int b = 20;
//     default public void func() {
//         System.out.println("接口");
//     }
// }

// class Cycly implements Shape {
//     @Override
//     public void draw() {
//         System.out.println("画一个⚪");
//     }
// }
// class Rectangle implements Shape {
//     @Override
//     public void draw() {
//         System.out.println("画一个 ⃞ ");
//     }
// }

// class Animal1 {
//     protected String name;
//     public Animal1(String name) {
//         this.name = name;
//     }

// }

// interface IFlying {//接口的名字用动词，接口可以实现多继承的方法
//     void flt();
// }
// interface ISwimming {
//     void swim();
// }
// interface IRunning {
//     void run();
// }

// class Fish extends Animal implements ISwimming {
//     public Fish(String name) {
//         super(name);
//     }

//     @Override
//     public void swim() {
//         System.out.println(this.name+"正在用尾巴游泳");
//     }
// }

// class Cat extends Animal implements IRunning {
//     @Override
//     public void run() {
//         System.out.println(this.name+"正在跑步");

//     }

//     public Cat(String name) {
//         super(name);
//     }
// }
// public class TestInterFace {
//     public static void walk(IRunning running) {
//         running.run();
//     }
//     public static void drawMap(Shape shape) {
//         shape.draw();
//     }

//     public static void main(String[] args) {
//         Shape shape = new Rectangle();
//         drawMap(shape);

//         IRunning iRunning = new Cat("小猫咪");
//         walk(iRunning);
//     }
// }
