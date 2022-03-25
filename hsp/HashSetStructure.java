package hsp;


class Node {
    Object item;
    Node next;
    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
    @Override
    public String toString() {
        return "Node [item=" + item + ", next=" + next + "]";
    }
    
}
@SuppressWarnings({"all"})
public class HashSetStructure {
    public static void main(String[] args) {
        
        Node[] table = new Node[16];
        Node Tommy = new Node("Tommy Shelby",null);
        table[2] = Tommy;

        Node jack = new Node("jack",null);
        Tommy.next = jack;//将jack挂载到Tommy

        Node rose = new Node("Rose",null);
        jack.next = rose;

        Node lucy = new Node("lucy",null);
        table[3] = lucy;


    }
}
