package hsp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings({"all"})
public class LinkedListCRUD {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("Tommy Shelby");
        set.add("Arthur Shelby");
        set.add("John Shelby");
        set.add(null);//添加的顺序和取出的顺序并不一致,是无序的,但固定
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }

        HashSet set1 = new HashSet();
        set1.add("Tommy Shelby");
        set1.add("Arthur Shelby");
        set1.add("John Shelby");
        set1.add(null);
        
        Iterator iterator1 = set1.iterator();
        while(iterator1.hasNext()) {
            Object obj = iterator1.next();
            System.out.println(obj);
        }
    }
}
