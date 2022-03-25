package bit;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MylinkedList {
    public Node head;//保存头节点的引用

    public void addFirst(int data) {//头插法
        Node node = new Node(data);

        if(this.head == null) {//第一次插入节点，头节点为空，进来
            this.head = node;
            return;
        }

        node.next = this.head;//头节点不为空，将旧的头节点的地址赋给新的头节点，新节点取代旧的节点
        this.head = node;//将头节点设置为新的
    }

    public void addList(int data) {//尾插法
        Node node = new Node(data);

        if(this.head == null) {//第一次插入节点，为空
            this.head = node;
            return;
        }
        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public boolean contains(int key) {//查抄是否包含关键字key是否在单链表中
        Node cur = this.head;
        while (cur != null) {
            if(cur.data == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int size() {//得到单链表的长度
        int count = 0;
        Node cur = this.head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        return count;
    }
    private Node searchIndex(int index) {
        if(index < 0 || index > this.size()) {
            throw new RuntimeException("index位置不合法");
        }
        Node cur = this.head;//index-1
        while(index - 1 != 0) {
            cur = cur.next;
            index--;
        }
        return cur;
    }

    public void addIndex(int index,int data) {//任意位置插入，第一个数据节点为0号下标
        if(index == 0) {
            this.addFirst(data);
            return;
        }

        if(index == this.size()) {
            this.addList(data);
            return;
        }

        Node node = new Node(data);
        Node cur = searchIndex(index);//先找到index位置 の 前一个节点的 の 地址
        //进行插入
        node.next = cur.next;
        cur.next = node;
    }

    private Node searchPrev(int key) {//返回key的前驱
        Node prev = this.head;
        while (prev.next != null) {
            if(prev.next.data == key) {
                return prev;
            } else {
                prev = prev.next;
            }
        }
        return null;
    }

    public void remove(int key) {//删除第一次出现关键字为key的节点
        if(this.head == null) {return;}
        if(this.head.data == key) {//如果要删除的是头节点
            this.head = this.head.next;
            return;
        }
        Node prev = searchPrev(key);
        if(prev == null) {
            System.out.println("没有这个节点");
            return;
        }
        Node del = prev.next;
        prev.next = del.next;
    }

    public void removeAllKey(int key) {//删除所有值为key的节点
        Node prev = searchPrev(key);
        Node cur = this.head.next;//代表要删除的节点
        while(cur != null) {
            if(cur.data == key) {
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = cur;
                cur = cur.next;
            }
        }
        if(this.head.data == key) {
            head.data = head.next.data;
        }
    }

    public void clear() {
        this.head = null;
    }

    public Node reverse() {//单链表的逆序，还用一种办法是头插法
        Node cur = this.head;
        Node prev = null;
        Node newHead = null;

        while(cur != null) {
            Node curNext = cur.next;

            if(curNext == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }

    public Node reverse2() {//用头插法实现单链表的逆序，没写完。。。。
        Node cur = this.head;
        this.head = null;

        while (cur != null) {
            this.head = cur.next;
            cur = cur.next;
        }
        return this.head;
    }

    public void reversedisplay(Node newHead) {//打印反转的单链表
        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }

    public void display() {//打印单链表
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
    }

    public Node middle() {//查找链表中间位置的节点
        Node fast = this.head;
        Node slow = this.head;
        while(fast!=null && fast.next!=null) {//两个判断条件不能交换，否则空指针异常
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node FindKthToTail(int k) {//查找倒数的数字
        Node fast = this.head;
        Node slow = this.head;
        if(head == null) {
            return null;
        }
        if(k <= 0) {
            System.out.println("k的值不合法");
            return null;
        }

        while(k-1 > 0) {
            if(fast.next != null) {
                fast = fast.next;
                k--;
            }else  {
                System.out.println("没有这个节点");
                return null;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public Node partition(int x){//将链表分割成两部分
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;
        Node cur = this.head;
        while(cur != null) {
            if(cur.data < x) {
                if(beforeStart == null) {//第一次插入
                     beforeStart = cur;
                     beforeEnd = cur;
                }else {//不是第一次插入
                    beforeEnd.next = cur;//尾插法
                    beforeEnd = beforeEnd.next;
                }


            }else {
                if(afterStart == null) {//第一次插入
                    afterStart = cur;
                    afterEnd = cur;
                }else {//不是第一次插入
                    afterEnd.next = cur;
                    afterEnd = afterEnd.next;
                }
            }
            cur = cur.next;
        }
        if(beforeStart == null) {//判断beforeStart是否为空，如果为空，返回afterStart
            return afterStart;
        }
        beforeEnd.next = afterStart;//拼接
        if(afterEnd != null) {//如果afterEnd不为空，afterEnd.next需要为空
            afterEnd.next = null;
        }
        return beforeStart;
    }

    public Node deleteDuplication() {//删除该链表中重复的结点
        Node cur = this.head;
        Node newHead = new Node(-1);
        Node tmp = newHead;

        while(cur != null) {
            if(cur.next != null && cur.data == cur.next.data) {
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                cur = cur.next;//多走一步
            }else {
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        tmp.next = null;
        return newHead.next;
    }

    public boolean chkPalindrome() {//链表的回文结构--头条
        if(this.head == null) {
            return false;
        }
        if(this.head.next == null) {
            return true;
        }
        Node fast = this.head;
        Node slow = this.head;
        while(fast!=null && fast.next!=null) {//找链表的中间节点
            slow = slow.next;
            fast = fast.next.next;
        }

        Node cur = slow.next;//反转单链表的后边部分
        while (cur != null) {
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }

        while(head != slow) {//开始一个从头走，一个从尾走
            if(head.data != slow.data) {
                return false;
            }

            if(this.head.next == slow) {//判断偶数的情况
                return true;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    public boolean hasCycle() {//判断链表中是否有环
        Node fast = this.head;
        Node slow = this.head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    public Node detectCycle() {//返回链表开始入环的第一个节点
        Node fast = this.head;
        Node slow = this.head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                break;
            }
        }
        if(fast == null || fast.next == null) {
            return null;
        }
        slow = this.head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
