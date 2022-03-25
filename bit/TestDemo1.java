package bit;
class Animal {
    protected String name;//既可以继承，又可以体现封装性

    public Animal(String name) {//子类在构造的时候，要先帮助父类进行构造
        this.name = name;
    }

    public void  eat() {
        System.out.println(this.name+"Animal::eat()");
    }
    public void sleep() {
        System.out.println("Animal::sleep()");
    }
}

class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    public void fly() {
        System.out.println(this.name+"Bird::fly()");
    }
}//子类，派生类--父类，基类，超类

class Dog extends Animal{
    public Dog(String name) {
        super(name);
    }
}


public class TestDemo1 {
    public static void main(String[] args) {
        Bird bird = new Bird("mimi");
        bird.name = "mimi";
        bird.eat();

        Animal animal = new Dog("豆豆");
        if(animal instanceof Dog) {
            bird.fly();
        }else {
            System.out.println("不是豆豆");
        }
    }
}
