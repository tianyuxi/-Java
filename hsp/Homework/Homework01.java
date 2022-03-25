package hsp.Homework;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class News {
    private String title;
    private String content;

    public News(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return "News [title=" + title + "]";
    }
}

class m {
    private String name;
    private int salary;
    public m(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "m [name=" + name + ", salary=" + salary + "]";
    }
    
}
@SuppressWarnings({"all"})


public  class Homework01 {

    public static String processTitle(String title) {
        if(title == null) {
            return"";
        }
        if(title.length() > 15) {
            return title.substring(0, 15)+"...";
        } else {
            return title;
        }
    }
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new News("新冠确诊病例超千万,数万年印度教徒赴汾河\"圣洗浴\"引民众担忧"));
        arrayList.add(new News("男子突然想起2个月前钓的鱼还在鱼兜里,老起来一看赶紧放生"));

        int size = arrayList.size();
        for (int i = size-1; i >= 0; i--) {
            News news = (News)arrayList.get(i);
            System.out.println(processTitle(news.getTitle()));
        }

        Map map = new HashMap();
        map.put("tom",500);
        map.put("smith",2900);

        map.put("tom", 2600);
        Set keySet = map.keySet();
        for (Object key : keySet) {
            map.put(key,(Integer)map.get(key)+100);
        }
        System.out.println(map);

        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        Collection values = map.values();
        for(Object value:values) {
            System.out.println(value);
        }
    }
}
