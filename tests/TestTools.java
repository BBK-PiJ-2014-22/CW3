package tests;

import DataStructures.FunctionalList;

public class TestTools {

	public static void buildList(FunctionalList list, int start, int end){
		for (int i = start; i < end ; i++){
			list.add(i);
		}
	}
		
}
