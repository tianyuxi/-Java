package bit;



class Stud implements Comparable<Stud>{
    public String name;
    public int age;
    public int score;

    @Override
    public String toString() {
        return super.toString();
    }

    public Stud(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;


    }

    @Override
    public int compareTo(Stud o) {
        if(this.age > o.age) {
            return 1;
        }else if(this.age == o.score) {
            return 0;
        }else {
            return -1;
        }
    }
}


public class Demo0224 {
    public static void main(String[] args) {
        Stud student1 = new Stud("小猫咪",18,100);
        Stud student2 = new Stud("高老师",29,70);
        if(student1.compareTo(student2) < 0) {
            System.out.println("student1的年龄 > studengt2");
        }

//        Stud[] Stud = new Stud[2];
//        Stud[0] = student1;
//        Stud[1] = student2;
//        Arrays.sort(Stud);
//        System.out.println(Arrays.toString(Stud));
    }
}
