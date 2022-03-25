package hsp;
import java.util.HashSet;

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {//构造器
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Employee [age=" + age + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {//重写hasCode
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {//重写equals
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        if (age != other.age) return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
@SuppressWarnings({"all"})
public class EmployeeTest {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new Employee("田宇希", 22));
        hashSet.add(new Employee("Harry Hacker", 23));
        hashSet.add(new Employee("田宇希", 22));
        System.out.println(hashSet);

    }
}
