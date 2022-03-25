package bit;




class MyValue {
    private int Value;

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }
}
class People{
    @SuppressWarnings({"all"})
    private String name;
    @SuppressWarnings({"all"})

    private int age;
    //两个构造方法
    public People() {
        this("caocao",18);
        System.out.println("Person<init>");
    }

    public People(String name,int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person<String.int>");
    }
    
    {
        System.out.println("实例代码块。。。。。");
    }

    static {
        System.out.println("静态代码块。。。。。");
    }
}



public class TestDemo4 {
    public static void main(String[] args) {
        MylinkedList mylinkedList = new MylinkedList();
        mylinkedList.addFirst(12);
        mylinkedList.addFirst(2);
        mylinkedList.addFirst(14);
        mylinkedList.addFirst(5);
        mylinkedList.addFirst(8);

        MylinkedList mylinkedList2 = new MylinkedList();
        mylinkedList2.addFirst(1);
        mylinkedList2.addFirst(2);
        mylinkedList2.addFirst(3);
        mylinkedList2.addFirst(4);
        mylinkedList2.addFirst(5);

        mylinkedList.FindKthToTail(3);

        mylinkedList.display();
        System.out.println();

        Node ret = mylinkedList.reverse();//逆序
        mylinkedList.reversedisplay(ret);//打印

        Node ret2 = getIntersectionNode(mylinkedList.head,mylinkedList2.head);
        System.out.println(ret2.data);
    }

    public static void swap(MyValue myValue1, MyValue myValue2) {
        int tmp = myValue1.getValue();
        myValue1.setValue(myValue2.getValue());
        myValue2.setValue(tmp);

    }

    public static void swapArr(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            int tmp = array1[i];
            array1[i] = array2[i];
            array2[i] = tmp;
        }
    }

    public static Node getIntersectionNode(Node headA, Node headB) {//输入两个链表，找出它们的第一个公共结点
        //求长度，计算差值
        int lenA = 0;
        int lenB = 0;
        Node pl = headA;
        Node ps = headB;
        while(pl != null) {
            lenA++;
            pl = pl.next;
        }
        while(ps != null) {
            lenB++;
            ps = ps.next;
        }

        pl = headA;
        ps = headB;
        int len = lenA - lenB;

        if(len < 0) {
            pl = headB;
            ps = headA;
            len = lenB - lenA;
        }
        for(int i = 0;i < len;i++) {
            pl = pl.next;
        }
        while(pl != ps && pl != null && ps != null) {
            ps = ps.next;
            pl = pl.next;
        }
        if(pl == ps && pl != null && ps != null) {
            return pl;
        }
        return null;
    }

    public Node mergeTwoLists(Node headA, Node headB) {// 合并两个有序链表
        Node newHead = new Node(-1);
        Node tmp = newHead;
        while(headA != null && headB !=null) {
            if(headA.data < headB.data) {
                tmp.next = headA;
                tmp = tmp.next;
                headA = headA.next;
            }else {
                tmp.next = headB;
                tmp = tmp.next;
                headB = headB.next;
            }
        }
        if(headA != null) {
            tmp.next = headA;
        }
        if(headB != null) {
            tmp.next = headB;
        }
        return newHead.next;
    }
}
//    public static void main(String[] args) {
//        People people1 = new People();//实例化一个对象，顺序:静态代码块先初始化，实例代码块初始化，构造方法,如果都是静态,看构造顺序
//
//        int[] array1 = {0,1,2,3,4};
//        int[] array2 = {5,6,7,8,9};
//        swapArr(array1,array2);
//        System.out.println(Arrays.toString(array1));
//        System.out.println(Arrays.toString(array2));
//
//        MyValue myValue1 = new MyValue();//交换两个值
//        myValue1.setValue(10);
//
//        MyValue myValue2 = new MyValue();
//        myValue2.setValue(20);
//
//        System.out.println(myValue1.getValue());
//        System.out.println(myValue2.getValue());
//        System.out.println("开始交换");
//        swap(myValue1,myValue2);
//
//        System.out.println(myValue1.getValue());
//        System.out.println(myValue2.getValue());
//
//        MyArraytList myArraytList = new MyArraytList();//new一个顺序表
//        for (int i = 0; i < 10; i++) {
//            myArraytList.add(i,i);
//        }
//        myArraytList.display();
//        System.out.println();
//
//        myArraytList.remove(0);
//        myArraytList.display();
//        System.out.println("----------------");
//
//        MylinkedList mylinkedList = new MylinkedList();//new一个链表
//        mylinkedList.addFirst(3);
//        mylinkedList.addFirst(5);
//        mylinkedList.addFirst(7);
//        mylinkedList.addFirst(9);
//        mylinkedList.remove(7);
//
//
//        System.out.println(mylinkedList.size());
//
//        mylinkedList.contains(5);
//
//        mylinkedList.display();
//    }
//}

