// package com.hsp.demo;
// class Node {
//     public Object item;
//     public Node next;
//     public Node prev;

//     public Node(Object name) {
//         this.item = name;
//     }
//     public String toString() {
//         return "Node name "+item;
//     }

// }
// public class LinkedList01 {
//     public static void main(String[] args) {
//         Node jack = new Node("jack");
//         Node tom = new Node("tom");
//         Node hsp = new Node("老韩");

//         jack.next = tom;
//         tom.next = hsp;

//         hsp.prev = tom;
//         tom.prev = jack;

//         Node first = jack;
//         Node last = hsp;

//         Node smith = new Node("smith");
//         smith.prev = tom;
//         smith.next = hsp;
//         hsp.prev = smith;
//         tom.next = smith;

//         while (true) {
//             if(first == null) {//当最后一个节点的nest为null时，退出循环
//                 break;
//             }
//             System.out.println(first);
//             first = first.next;//从头到尾
//         }
//         while(true) {
//             if(last == null) {
//                 break;
//             }
//             System.out.println(last);
//             last = last.prev;
//         }
//     }
// }
