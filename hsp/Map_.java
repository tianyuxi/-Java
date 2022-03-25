package hsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@SuppressWarnings({"all"})
public class Map_ {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("邓超", "孙俪");
        map.put("王宝强", "马蓉");
        map.put("宋吉", "马蓉");
        map.put("刘令播", null);
        map.put(null, "刘亦菲");
        map.put("鹿晗", "关晓彤");

        System.out.println("=====使用keySet=====");


        Set keyset = map.keySet();
        for (Object key : keyset) {
            System.out.println(key+" "+map.get(key));
        }

        Iterator iterator = keyset.iterator();
        while(iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key);
        }

        Collection values = map.values();//使用所有的Collections的遍历方法
        for (Object value : values) {
            System.out.println(value);
        }
        Iterator iterator1 = values.iterator();
        while(iterator1.hasNext()) {
            Object value = iterator1.next();
            System.out.println(value);
        }
        System.out.println("=====使用EntrySet=====");
        Set entrySet = map.entrySet();
        for(Object entry : entrySet) {
            Map.Entry m = (Map.Entry)entry;
            System.out.println(m.getKey()+""+m.getValue());
        }

        Iterator iterator2 = entrySet.iterator();
        while(iterator2.hasNext()) {
            Object entry = iterator2.next();
            Map.Entry m = (Map.Entry)entry;
            System.out.println(m.getValue());
        }

    }
}
