package tests;
import datastructures.AbstractStack;
import datastructures.ArrayList;
import datastructures.ErrorMessage;
import datastructures.FunctionalList;
import datastructures.ImprovedStack;
import datastructures.ImprovedStackImpl;
import datastructures.LinkedList;
import datastructures.List;
import datastructures.ReturnObject;
import datastructures.ReturnObjectImpl;
import datastructures.SampleableList;
import datastructures.SampleableListImpl;
import datastructures.Stack;
import datastructures.StackImpl;


public class TestCW3 {

	public static void main(String[] args) {
		TestCW3 test = new TestCW3();
		test.launch();
	}
	
	public void launch(){
		
		//Tests the return objects are all working correctly
		testReturnObject(new ReturnObjectImpl("str"	, ErrorMessage.NO_ERROR)			,"str"	, ErrorMessage.NO_ERROR			,false, "Return Object Test 1 Failed");
		testReturnObject(new ReturnObjectImpl(1		, ErrorMessage.NO_ERROR)			,1		, ErrorMessage.NO_ERROR			,false, "Return Object Test 2 Failed");
		testReturnObject(new ReturnObjectImpl(1.0	, ErrorMessage.NO_ERROR)			,1.0	, ErrorMessage.NO_ERROR			,false, "Return Object Test 3 Failed");
		testReturnObject(new ReturnObjectImpl(true	, ErrorMessage.NO_ERROR)			,true	, ErrorMessage.NO_ERROR			,false, "Return Object Test 4 Failed");
		testReturnObject(new ReturnObjectImpl(null	, ErrorMessage.NO_ERROR)			,null	, ErrorMessage.NO_ERROR			,false, "Return Object Test 5 Failed");
		testReturnObject(new ReturnObjectImpl("str"	, ErrorMessage.EMPTY_STRUCTURE)		,null	, ErrorMessage.EMPTY_STRUCTURE	,true, 	"Return Object Test 6 Failed");
		testReturnObject(new ReturnObjectImpl("str"	, ErrorMessage.INDEX_OUT_OF_BOUNDS)	,null	, ErrorMessage.INDEX_OUT_OF_BOUNDS,true,"Return Object Test 7 Failed");
		testReturnObject(new ReturnObjectImpl("str"	, ErrorMessage.INVALID_ARGUMENT)	,null	, ErrorMessage.INVALID_ARGUMENT	,true,	"Return Object Test 8 Failed");

		//Tests on other data structures. All tests have the form of "Tag" and "Object"
		//"Tag" = message to display when error found, "Object" = object to test
		testList(    "Array  List",			new ArrayList());
		testList(    "Linked List", 		new LinkedList());		
		testSampList("Sample List", 		new SampleableListImpl());
		testStack(	 "Array  Stack", 		new StackImpl(new ArrayList()));
		testStack(	 "Linked Stack", 		new StackImpl(new LinkedList()));
		testStack(	 "Array (Imp) Stack", 	new ImprovedStackImpl(new ArrayList()));
		testStack(	 "Linked (Imp)Stack", 	new ImprovedStackImpl(new LinkedList()));
		testImpStack("Imp Stack (Array)",  	new ImprovedStackImpl(new ArrayList()));
		testImpStack("Imp Stack (Linked)", 	new ImprovedStackImpl(new LinkedList()));

		//See JUnit for FunctionaList tests
		
		System.out.println("Tests Completed");
	
	}
	
	/***Tests a specific ReturnObject against each of the methods within it 
	 * 
	 * @param testObject ReturnObject to be tested
	 * @param object Value which the ReturnObject should have
	 * @param error ErrorMessage thats should be returned
	 * @param result Expected result of the hasError() function
	 * @param message Message identify the test to display if an error is found
	 */
	public void testReturnObject(ReturnObject testObject, Object object, ErrorMessage error, boolean result, String message){
		
		boolean hasErrorTestFail = false;
		boolean getReturnValueTestFail = false;
		boolean getErrorTestFail = false;
		
		//tests that the getError message will match to the intended message
		if (!error.equals(testObject.getError()))
			getErrorTestFail = true;
		//tests whether the hasError is returning correctly
		if (result != testObject.hasError())
			hasErrorTestFail = true;
		//tests that the return object is returning correctly
		if ((object == null && testObject.getReturnValue() != null)||
			 object != null && !object.equals(testObject.getReturnValue()))
			getReturnValueTestFail = true;		
		
		//Prints messages if necessary
		if (getErrorTestFail || hasErrorTestFail || getReturnValueTestFail){
			System.out.println(message);
			System.out.println(testObject);
			System.out.println("[Test Value: "+object+" Test Error: "+error+"]");
			if (getErrorTestFail) 
				System.out.println("getError Test Failed");
			if (hasErrorTestFail) 
				System.out.println("hasError Test Failed");
			if (getReturnValueTestFail) 
				System.out.println("getReturnValue Test Failed");

		}
	}
	
	/***Performs a series of tests on an object which implements List. Will display error messages on failure, 
	 * and display time taken for completion of the test.
	 * 
	 * @param tag Identifier for the specific test to be displayed on fail and/or completion
	 * @param list Specific List implementation to be tested
	 */
	public void testList(String tag, List list){
		
		long startTime = System.nanoTime();
	
	
		testListIsEmpty(list, true, tag+" initial isEmpty test");		
		TestTools.buildList(list, 0, 9);
		testListPart(list,  tag+" add (no index) 1"	, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.add(9)			 , null, ErrorMessage.NO_ERROR);
		testListCopy(list,  tag+"copy test");
		testListPart(list,  tag+" add (no index) 2"	, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.add(null)		 , null, ErrorMessage.INVALID_ARGUMENT);
		testListPart(list,  tag+" add (index) 1"	, "[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", 11, list.add(2,"two")	 , null, ErrorMessage.NO_ERROR);
		testListPart(list,  tag+" add (index) 2"	, "[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", 11, list.add(12,"twelve"), null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		testListPart(list,  tag+" add (index) 3"	, "[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", 11, list.add(-1,"-one")	 , null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		testListPart(list,  tag+" add (index) 4"	, "[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", 11, list.add(2,null)	 , null, ErrorMessage.INVALID_ARGUMENT);
		testListPart(list,  tag+" add (index) 5"	, "[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", 11, list.add(-1,null)	 , null, ErrorMessage.INVALID_ARGUMENT);
		testListPart(list,  tag+" remove test 1"	, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.remove(2)	 	 ,"two", ErrorMessage.NO_ERROR);
		testListPart(list,  tag+" remove test 2"	, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.remove(-1)	 	 , null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		testListPart(list,  tag+" remove test 3"	, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.remove(12)	 	 , null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		testListPart(list,  tag+" get test 1"		, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.get(3)	 	 	 , 3   , ErrorMessage.NO_ERROR);
		testListPart(list,  tag+" get test 2"		, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.get(-1)	  	 , null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		testListPart(list,  tag+" get test 3"		, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.get(12)	 	 , null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		testListPart(list,  tag+" remove test 4"	, "[0:1, 1:2, 2:3, 3:4, 4:5, 5:6, 6:7, 7:8, 8:9]"			  , 9 , list.remove(0)	 	 ,0	   , ErrorMessage.NO_ERROR);
		
		TestTools.breakList(list);

		testListIsEmpty(list, true, tag+"final isEmpty test");
	
		long endTime = System.nanoTime();
		System.out.println("###"+tag+" took "+(endTime - startTime) + " ns###");
		System.out.println();
		
		
	}
		
	//Sub elements of List test
	
	/**Tests and function of List that returns a ReturnObject against a range of aspects to ensure that
	 * the list is in the correct state. Will print the tag and an error message for any errors found.
	 * Is in turn made up of other sub List tests
	 * 
	 * @param list List to test
	 * @param tag Identifier for the specific test to be displayed on fail
	 * @param endListState String matching the expected toString of the list after the tested function has been performed
	 * @param endListSize Expected size of the string after the tested function has been performed
	 * @param function Function to test
	 * @param returnValue Expected value to be returned by the function
	 * @param returnError Expected error message to be returned by the function
	 */
	void testListPart(List list, String tag, String endListState, int endListSize, ReturnObject function, Object returnValue, ErrorMessage returnError){
		
		Boolean hasError = true;
		
		if (returnError.equals(ErrorMessage.NO_ERROR))
			hasError = false;
		
		testListMatch(list,endListState, tag);
		testReturnObject(function, returnValue, returnError, hasError, "Return Object for "+tag+" failed");
		testListSize(list, tag, endListSize);
		testListIsEmpty(list, false, tag);		
	}
	
	/**Tests that the size of the list is the expected size
	 * 
	 * @param list List to test
	 * @param tag Identifier for the specific test to be displayed on fail
	 * @param size Expected size of list
	 */
	void testListSize(List list, String tag, int size){
		if (list.size() != size){
			System.out.println(tag+ "Size test failed");
			System.out.println("Expected:"+list.size());
		    System.out.println("Actual:  "+size);
		}
	}
	
	/**Tests that the list's toString matches to the expected value
	 * 
	 * @param list List to test
	 * @param match Expected toString
	 * @param tag Identifier for the specific test to be displayed on fail
	 */
	void testListMatch(List list, String match, String tag){
		if (!list.toString().equals(match)){
			System.out.println(tag+" match test failed - output string does not match");
			System.out.println("Expected:"+match);
			System.out.println("Actual:  "+list);		
		}		
	}
	/**Tests if a list's isEmpty() function is returning the correct result
	 * 
	 * @param list List to test
	 * @param value Expected return value
	 * @param tag Identifier for the specific test to be displayed on fail
	 */
	void testListIsEmpty(List list, boolean value, String tag){
		if (!list.isEmpty() == value){
			System.out.println(tag + " isEmpty test failed");
			System.out.println("Expected:"+value);
			System.out.println("Actual:  "+list.isEmpty());
		}
	}
	
	/**Tests if a list can be copied. Tests all lists against both linked and array lists.
	 * 
	 * @param list List to be copied
	 * @param tag Identifier for the specific test to be displayed on fail
	 */
	void testListCopy(List list, String tag){
		
		List arrayCopy = new ArrayList(list);
		List linkedCopy = new LinkedList(list);
		
		if (!arrayCopy.toString().equals(list.toString())){
			System.out.println(tag + "arrayCopy failed");
			System.out.println("Expected:"+list);
			System.out.println("Actual:  "+arrayCopy);
		}
		if (!linkedCopy.toString().equals(list.toString())){
			System.out.println(tag + "linkedCopy failed");
			System.out.println("Expected:"+list);
			System.out.println("Actual:  "+linkedCopy);
		}
	}
	
	
	//SampleableList tests
	
	/**Tests SampleableList implementations against List functionality and extended features
	 * 
	 * @param tag Identifier for the specific test to be displayed on fail and/or completion
	 * @param list List implementation to be tested
	 */
	void testSampList(String tag, SampleableList list){
	
		testList(tag, list);
		
		TestTools.buildList(list, 0, 9);
		
		SampleableList newList = list.sample();
		testListMatch(newList, "[0:0, 1:2, 2:4, 3:6, 4:8]", tag+ " sample origional match test");
		
		list.remove(1);
		testListMatch(newList, "[0:0, 1:2, 2:4, 3:6, 4:8]", tag+ " sample list independent to original");		
		
		newList.remove(1);
		testListMatch(list, "[0:0, 1:2, 2:3, 3:4, 4:5, 5:6, 6:7, 7:8]", tag+ " original list independent to sample");		
	}
	
	//Stack tests
	
	/**Series of tests to test basic functionality of any Stack implementation. Displays time taken at end of test. 
	 * 
	 * @param tag Identifier for the specific test to be displayed on fail and/or completion
	 * @param stack Stack object to be tested
	 */
	void testStack(String tag, Stack stack){
		long startTime = System.nanoTime();

		testStackIsEmpty(tag, stack, true);
		
		testStackPush(tag + " push test 1", stack, 1, "[0:1]",1);
		testStackPush(tag + " push test 2", stack, 2, "[0:1, 1:2]",2);
		testStackPush(tag + " push test 3", stack, 3, "[0:1, 1:2, 2:3]",3);
		testStackPush(tag + " push test 4", stack, 4, "[0:1, 1:2, 2:3, 3:4]",4);
		testStackPush(tag + " push test 5", stack, 5, "[0:1, 1:2, 2:3, 3:4, 4:5]",5);
		testStackPop( tag + " pop test 1" , stack, stack.pop(), ErrorMessage.NO_ERROR, 5, false, "[0:1, 1:2, 2:3, 3:4]",4, 4, false);
		testStackPop( tag + " pop test 2" , stack, stack.pop(), ErrorMessage.NO_ERROR, 4, false, "[0:1, 1:2, 2:3]",3, 3, false);
		testStackPop( tag + " pop test 3" , stack, stack.pop(), ErrorMessage.NO_ERROR, 3, false, "[0:1, 1:2]",2, 2, false);
		testStackPop( tag + " pop test 4" , stack, stack.pop(), ErrorMessage.NO_ERROR, 2, false, "[0:1]",1, 1, false);
		testStackPop( tag + " pop test 5" , stack, stack.pop(), ErrorMessage.NO_ERROR, 1, false, "[null]",0, null, true);
		testStackPop( tag + " pop test 6" , stack, stack.pop(), ErrorMessage.EMPTY_STRUCTURE, null, true, "[null]",0, null, true);
		
		long endTime = System.nanoTime();
		System.out.println("###"+tag+" took "+(endTime - startTime) + " ns###");
		System.out.println();
	}
	
	//Stack Compononents
	
	/**Tests if a stack's isEmpty() function is working. Includined in Push/Pop tests
	 * 
	 * @param tag Identifier for the specific test to be displayed on fail 
	 * @param stack Stack object to be tested
	 * @param value Expected return value
	 */
	void testStackIsEmpty(String tag, Stack stack, boolean value){
		if (!stack.isEmpty() == value){
			System.out.println(tag + " isEmpty test failed");
			System.out.println("Expected:"+value);
			System.out.println("Actual:  "+stack.isEmpty());
		}
	}

	void testStackPop(String tag, Stack stack, ReturnObject returnValue, ErrorMessage error, 
			Object returnedValue, boolean hasError, String match, int size, Object top, boolean isEmpty){
	
		testStackMatch(tag,stack,match,size, top);
		testReturnObject(returnValue, returnedValue, error, hasError, tag+"return object test failed");
		testStackIsEmpty(tag, stack, isEmpty);
	}
	
	void testStackPush(String tag, Stack stack, Object push, String match, int size){
		
		stack.push(push);
		testStackMatch(tag, stack, match, size, push);
		testStackIsEmpty(tag, stack, false);
	}

	void testStackMatch(String tag, Stack stack, String match, int size, Object top){
		
		if (!stack.toString().equals(match)){
			System.out.println(tag +": match test failed");
			System.out.println("Target: "+match);
			System.out.println("Actual: "+stack);
		}if (stack.size() != size){
			System.out.println(tag +": size test failed");
			System.out.println("Target: "+size);
			System.out.println("Actual: "+stack.size());
		}
		
		boolean topTestFailed = false;

		if (top == null){
			if (top != stack.top().getReturnValue()){
				topTestFailed = true;
			}
		}else if (!stack.top().getReturnValue().equals(top)){
			topTestFailed = true;
		}
		if (topTestFailed){
			System.out.println(tag +": top test failed");
			System.out.println("Target: "+top);
			System.out.println("Actual: "+stack.top().getReturnValue());			
		}

	}
	
	void testStackCopy(String tag, AbstractStack stack){
		Stack copy = new StackImpl(stack);
		
		if (!stack.toString().equals(copy.toString())){
			System.out.println(tag +": Stack Copy test failed");
			System.out.println("Target: "+stack);
			System.out.println("Actual: "+copy);
		}
	}

	

	void testImpStack(String tag, ImprovedStack stack){
		long startTime = System.nanoTime();
		
			
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		testImprovedStackReverse(tag+" Reverse test 1", stack, "[0:0, 1:1, 2:2, 3:3, 4:4]", "[0:4, 1:3, 2:2, 3:1, 4:0]", 5, 4, 0);
		testImprovedStackRemove(tag+" Remove test 1", stack, "[0:0, 1:1, 2:2, 3:3, 4:4]", 5, 4, 5 );
		testImprovedStackRemove(tag+" Remove test 2", stack, "[0:0, 1:1, 2:2, 3:3]", 4, 3, 4);
		testImprovedStackRemove(tag+" Remove test 3", stack, "[0:0, 1:1, 2:3]", 3, 3, 2);
		
		stack.push(2);
		stack.push(2);
		
		testImprovedStackRemove(tag+" Remove test 4", stack, "[0:0, 1:1, 2:3]", 3, 3, 2);
		testImprovedStackRemove(tag+" Remove test 5", stack, "[0:1, 1:3]", 2, 3, 0);
		testImprovedStackReverse(tag+" Reverse test 2", stack, "[0:1, 1:3]", "[0:3, 1:1]", 2, 3, 1);
		
		long endTime = System.nanoTime();
		System.out.println("###"+tag+" took "+(endTime - startTime) + " ns###");
		System.out.println();
	}	

	void testImprovedStackRemove(String tag, ImprovedStack stack, String match, int size, Object top, Object removeObject){
		
		stack.remove(removeObject);
		testStackMatch(tag, stack, match, size, top);
		
	}
	
	void testImprovedStackReverse(String tag, ImprovedStack stack, String matchOld, String matchNew, int size, Object topOld, Object topNew){
		
		ImprovedStack reverseStack = stack.reverse();
		testStackMatch(tag+" Original ", stack, matchOld, size, topOld);
		testStackMatch(tag+" Reverse ", reverseStack, matchNew, size, topNew);
		
	}
	
}

