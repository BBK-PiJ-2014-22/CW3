package tests;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import datastructures.ErrorMessage;
import datastructures.FunctionalArrayList;
import datastructures.FunctionalLinkedList;
import datastructures.FunctionalList;
import datastructures.ReturnObject;
import datastructures.ReturnObjectImpl;

@RunWith(Parameterized.class)
public class FunctionalListTest {

	FunctionalList list;
	Class listClass;
	
	@Parameters
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { FunctionalArrayList.class }, { FunctionalLinkedList.class }  
           });
    }

	public FunctionalListTest(Class classParam){
		this.listClass = classParam;
	}
	
	@Before
	public void setUp() throws Exception {
		list = (FunctionalList)listClass.newInstance();
	}

	@Test
	public void headBasicFunction() {
		
		TestTools.buildList(list, 0, 5);		
		
		ReturnObject result = list.head();
		assertEquals(new ReturnObjectImpl(0), result);		
	}

	@Test
	public void headNullFunction(){
		ReturnObject result = list.head();
		assertEquals(new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE), result);
	}	
	
	/*
	@Test
	public void headIdentical(){
		//Tests if object is identical
	}
	*/
	
	@Test
	public void restStandard(){
		
		TestTools.buildList(list, 0,5);
		
		FunctionalList rest = list.rest();		
		FunctionalList comparison = new FunctionalArrayList();
		
		TestTools.buildList(comparison, 1, 5);
		
		assertEquals(comparison, rest);
	}
	
	@Test
	public void restEmptyList(){
		
		FunctionalList match = new FunctionalArrayList();
		FunctionalList result = list.rest();
		assertEquals(match, result);
	}
	
	@Test
	public void restSingleElement(){
		TestTools.buildList(list, 0,1);
		
		FunctionalList rest = list.rest();		
		FunctionalList comparison = new FunctionalArrayList();
		
		assertEquals(comparison, rest);
		
	}
	
	@Test
	public void restListChangeOriginal(){
		
		FunctionalList match = new FunctionalArrayList();
		
		TestTools.buildList(list, 0,5);
		TestTools.buildList(match,0,5);
		

		FunctionalList rest = list.rest();		
		
		TestTools.buildList(rest, 1,5);
		
		assertEquals(match, list);
	}
	
	@Test
	public void restListChangeRest(){
		
		FunctionalList match = new FunctionalArrayList();
		
		TestTools.buildList(list, 0,5);
		TestTools.buildList(match,1,5);
		

		FunctionalList rest = list.rest();		
		
		TestTools.buildList(list, 1,5);
		
		assertEquals(match, rest);
	}
	

}
