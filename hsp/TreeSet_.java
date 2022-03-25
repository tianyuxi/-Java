package hsp;

import java.util.Comparator;
import java.util.TreeMap;
@SuppressWarnings({"all"})
public class TreeSet_ {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap(new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                //return ((String)o1).compareTo((String)o2);
                return ((String)o1).length()-((String)o2).length();//字符串长度
            }
        });
        treeMap.put("jack","杰克");
        treeMap.put("tom","汤姆");
        treeMap.put("Kristina","克瑞斯提诺");
        treeMap.put("smith","斯密斯");
        System.out.println(treeMap);
    }
}
