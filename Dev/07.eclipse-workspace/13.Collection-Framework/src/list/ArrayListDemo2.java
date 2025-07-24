package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo2 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		// list 계열 : 순서 있음. 중복 허용
		list.add("orange");
		list.add("apple");
		list.add("apple");
		list.add("banana");
		
//		for(int i = 0; i < list.size(); i++) 
//			System.out.print(list.get(i) + "\t");
		
		// 반복자 
		Iterator<String> itr = list.iterator();
		String str0;
		while(itr.hasNext()) {
			str0 = itr.next();
			System.out.print(str0 + "\t");
		}
		
		System.out.println();
		
		list.remove(0);
		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i) + "\t");
		
	}
}
