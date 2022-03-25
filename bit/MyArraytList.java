package bit;

import java.util.Arrays;

public class MyArraytList {
    //两个属性
    public int[] elem;//数组,也可以放Person
    public int usedSize;//有效的数据个数
    public static final int capacity = 10;//初始容量

    public MyArraytList() {//构造方法
        this.elem = new int[capacity];//数组一定初始化
        this.usedSize = 0;
    }
    private boolean isFull() {//查看数组是否满
//        if(this.elem.length==this.usedSize) {
//            return true;
//        }
//        return false;
        return this.elem.length==this.usedSize;
    }
    private void checkPos (int pos) {//检查合法性
        if(pos < 0 || pos > this.usedSize) {
            throw new RuntimeException("Pos位置不合法");
        }
    }
    public void add(int pos,int data) {
        checkPos(pos);
        if(isFull()) {//如果满了，数组长度扩大二倍
            this.elem= Arrays.copyOf(this.elem,2*this.elem.length);
        }
        for(int i = this.usedSize-1;i > pos;i--) {
            this.elem[i+1]=this.elem[i];
        }
        elem[pos]=data;
        this.usedSize++;
    }

    public void display() {//打印顺序表
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i]+" ");
        }
    }
    public boolean contains(int toFind) {
        for (int i = 0; i < this.elem.length; i++) {
            if(this.elem[i]==toFind) {
                return true;
            }
        }
        return false;
    }
    public int search(int toFind) {
        for(int i = 0;i < this.elem.length;i++) {
            if(this.elem[i]==toFind) {
                return i;
            }
        }
        return -1;
    }
    @SuppressWarnings({"all"})


    private boolean isEmpty() {

        return this.usedSize == 0;
    }
    public int getPos(int pos) {//获取pos位置的元素1,是否为空，2,是否合法
        checkPos(pos);
        return this.elem[pos];

    }

    public int size() {//获取顺序表的长度
        return this.usedSize;
    }

    public void remove(int toRemve) {//删除第一次出现的关键字key
        int index = search(toRemve);
        if(index==-1){
            System.out.println("没有要删除的数字");
        }
        for (int i = index; i < usedSize-1; i++) {
            this.elem[i]=this.elem[i+1];
        }
        this.usedSize--;
    }

    public void clear() {//清空顺序表
        this.usedSize=0;
    }
}
