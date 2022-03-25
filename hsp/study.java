// package com.hsp.demo;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;

// import java.util.Iterator;

// class book {
//     private String name;
//     private String author;
//     private double price;

//     public book(String name, String author, double price) {//构造方法
//         this.name = name;
//         this.author = author;
//         this.price = price;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getAuthor() {
//         return author;
//     }

//     public void setAuthor(String author) {
//         this.author = author;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }

//     @Override
//     public String toString() {
//         return "book [author=" + author + ", name=" + name + ", price=" + price + "]";
//     }
    
// }

// public class study {
//     @SuppressWarnings({"all"})
//     public static void  main(String[] args) {
//         List list = new ArrayList();
//         list.add("10");//list.add(new Integer(10))
//         list.add(true);
//         System.out.println(list);

//         Collection col = new ArrayList();
//         col.add(new book("三国演义","罗贯中",10.1));
//         col.add(new book("小李飞刀","古龙",5.1));
//         col.add(new book("红楼梦","曹雪芹",34.6));

//         Iterator iterator = col.iterator();//得到col对应的迭代器
//         while(iterator.hasNext()) {//判断是否还有数据返回下一个元素,类型是Object
//             Object obj = iterator.next();
//             System.out.println(obj);
//         }
//         //如果希望再次遍历,需要重置迭代器
//         iterator = col.iterator();//得到col对应的迭代器
//         while(iterator.hasNext()) {//判断是否还有数据返回下一个元素,类型是Object
//             Object obj = iterator.next();
//             System.out.println(obj);
//         }
//         for(Object book:col) {//增强for循环,可以替代iterator迭代器,就是简化的iterator,本质一样(元素类型 元素名:集合名或数组名)
//             System.out.println(book);

//         }
//     }
// }
