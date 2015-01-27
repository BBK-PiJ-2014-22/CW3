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
	public void headBasicFunctionReturn() {
		list.add(1);
		list.add(2);
		list.add(3);
		ReturnObject result = list.head();
		assertEquals(1, result.getReturnValue());		
	}

	@Test
	public void headBasicFunctionError() {
		list.add(1);
		list.add(2);
		list.add(3);
		ReturnObject result = list.head();
		assertEquals(ErrorMessage.NO_ERROR, result.getError());		
	}

	@Test
	public void headNullFunction(){
		ReturnObject result = list.head();
		assertEquals(ErrorMessage.EMPTY_STRUCTURE, result.getError());
	}
	
	
	
	
}
