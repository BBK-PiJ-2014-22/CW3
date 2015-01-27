package tests;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import DataStructures.ErrorMessage;
import DataStructures.FunctionalArrayList;
import DataStructures.FunctionalLinkedList;
import DataStructures.FunctionalList;
import DataStructures.ReturnObject;
import DataStructures.ReturnObjectImpl;


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
		list.add(1);
		list.add(2);
		list.add(3);
		ReturnObject result = list.head();
		assertEquals(new ReturnObjectImpl(1), result);		
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
	
	/*@Test
	public void restStandard(){
		list.add("one");
		list.add("two");
		list.add("three");
		
		FunctionaList rest = list.rest();
		
		
	}*/
}
