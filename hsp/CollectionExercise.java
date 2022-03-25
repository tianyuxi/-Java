package hsp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Dog {
    private String name;
    private int age;
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Dog [age=" + age + ", name=" + name + "]";
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
}

public class CollectionExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Dog("豆豆",1)); 
        list.add(new Dog("黑黑",2));
        list.add(new Dog("勾勾",3));
        for(Object dog:list) {
            System.out.println(dog);
        }
        System.out.println("=====使用迭代器======");
        Iterator iterable = list.iterator();
        while(iterable.hasNext()) {
            Object obj = iterable.next();
            System.out.println(obj);
        }


        List list1 = new ArrayList();
        list1.add("Tom");
        list1.add("Alice");
        list1.add("Jack");
        list1.add("Shelly");
        list1.add("Thomas");
        list1.add("Jams");
        list1.add("Dick");
        list1.add(1,"韩顺平教育");
        list1.get(4);
        list1.remove(5);
        list1.set(6,"修改了");

        Iterator iterator = list1.iterator();
        while(iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }
}
