package net.jcip.my;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @program: jcip
 * @author: daniel
 * @create: 2021-08-17 14:33
 **/
public class IterateDemo {
	public static void main(String[] args) {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
		concurrentHashMap.values().iterator();
		// get the iterator
		Iterator<String> it = list.iterator();

		//manipulate list while iterating
		while(it.hasNext()){
			System.out.println("list is:"+list);
			String str = it.next();
			System.out.println("iterator:" + str);
			//			if(str.equals("2"))list.remove("5");
			if(str.equals("3"))list.add("3 found");
			//below code don't throw ConcurrentModificationException
			//because it doesn't change modCount variable of list
			//			if(str.equals("4")) list.set(1, "4");
		}

		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

		map.put("First", 10);
		map.put("Second", 20);
		map.put("Third", 30);
		map.put("Fourth", 40);

		Iterator<String> iterator = map.keySet().iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key);

			map.put("Fifth", 50);
		}
	}
}
