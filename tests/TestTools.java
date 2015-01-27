package tests;

import datastructures.List;

public class TestTools {

	public static void buildList(List list, int start, int end){
		for (int i = start; i < end ; i++)
			list.add(i);
	}
	
	public static void breakList(List list){
		while (!list.isEmpty())
			list.remove(0);
	}
		
}
