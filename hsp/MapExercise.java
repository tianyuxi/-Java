package hsp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Staff {
    private String name;
    private double salary;
    private int id;
    public Staff(String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "staff [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
    
}
@SuppressWarnings({"all"})
public class MapExercise {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(1,new Staff("jack", 300000, 1));
        map.put(2,new Staff("tom", 21000, 2));
        map.put(3,new Staff("milan", 12000, 3));

        System.out.println("=====使用keySet=====");

        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();
        while(iterator.hasNext()) {
            Object o = iterator.next();
            System.out.println(o+""+map.get(o));
        }

        for(Object key : keySet) {
            Staff staff = (Staff)map.get(key);
            if(staff.getSalary() > 18000) {
                System.out.println(staff);
            }
        }


        System.out.println("=====使用EntrySet=====");
        Set entrySet = map.entrySet();
        iterator = entrySet.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            Staff staff = (Staff)entry.getValue();
            if(staff.getSalary() > 18000) {
                System.out.println(staff);
            }
        }
      

        // for(Object key : entrySet) {
        //     Staff staff = (Staff)map.get(key);
        //     if(staff.getSalary() > 18000) {
        //         System.out.println(staff);
        //     }
        // }//NullPointerExcept
    }
}
