package hsp;


import java.util.*;
@SuppressWarnings({"all"})
class Book {
    private String name;
    private String author;
    private int price;

    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }
    @Override
    public String toString() {
        return "名称:"+name+"\t\t价格:"+price+"\t\t作者:"+author;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

}
public class ListExercise {
    @SuppressWarnings({"all"})

    public static void main(String[] args) {
        List list = new ArrayList(8);
        //List list = new LinkedList();
        //List list = new Vector();
        list.add(new Book("三国演义","罗贯中",10));
        list.add(new Book("Java核心技术","霍斯曼特",149));
        list.add(new Book("第一行代码","郭霖",99));
        list.add(new Book("C Primer Plus","Stephen Prata",108));

        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            Object book = iterator.next();
            System.out.println(book);
        }
        sort(list);
        for(Object o:list) {
            System.out.println(o);
        }
    }
    @SuppressWarnings({"all"})
    public static void sort(List list) {//冒泡排序
        int listSize = list.size();
        for(int i = 0; i < listSize-1;i++) {
            for(int j = 0;j < listSize-i-1;j++) {
                Book book1 = (Book)list.get(j);
                Book book2 = (Book)list.get(j+1);
                if(book1.getPrice()>book2.getPrice()) {//价格比较
                    list.set(j,book2);
                    list.set(j+1,book1);
                }
            }
        }
    }
}
