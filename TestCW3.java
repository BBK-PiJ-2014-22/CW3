
public class TestCW3 {

	public static void main(String[] args) {
		TestCW3 test = new TestCW3();
		test.launch();
	}
	
	public void launch(){
		
		testReturnObjectImpl(new ReturnObjectImpl("str"	, ErrorMessage.NO_ERROR)			,"str"	, ErrorMessage.NO_ERROR			,false, "Return Object Test 1 Failed");
		testReturnObjectImpl(new ReturnObjectImpl(1		, ErrorMessage.NO_ERROR)			,1		, ErrorMessage.NO_ERROR			,false, "Return Object Test 2 Failed");
		testReturnObjectImpl(new ReturnObjectImpl(1.0	, ErrorMessage.NO_ERROR)			,1.0	, ErrorMessage.NO_ERROR			,false, "Return Object Test 3 Failed");
		testReturnObjectImpl(new ReturnObjectImpl(true	, ErrorMessage.NO_ERROR)			,true	, ErrorMessage.NO_ERROR			,false, "Return Object Test 4 Failed");
		testReturnObjectImpl(new ReturnObjectImpl(null	, ErrorMessage.NO_ERROR)			,null	, ErrorMessage.NO_ERROR			,false, "Return Object Test 5 Failed");
		testReturnObjectImpl(new ReturnObjectImpl("str"	, ErrorMessage.EMPTY_STRUCTURE)		,null	, ErrorMessage.EMPTY_STRUCTURE	,true, 	"Return Object Test 6 Failed");
		testReturnObjectImpl(new ReturnObjectImpl("str"	, ErrorMessage.INDEX_OUT_OF_BOUNDS)	,null	, ErrorMessage.INDEX_OUT_OF_BOUNDS,true,"Return Object Test 7 Failed");
		testReturnObjectImpl(new ReturnObjectImpl("str"	, ErrorMessage.INVALID_ARGUMENT)	,null	, ErrorMessage.INVALID_ARGUMENT	,true,	"Return Object Test 8 Failed");

		//testList("Dummy List", new DummyList());
		testList("Array List", new ArrayList());
		testList("Linked List", new LinkedList());
		testSampleableList("Sampleable List", new SampleableListImpl());
		
		testStack("Array Stack", new StackImpl(new ArrayList()));
		testStack("Linked Stack", new StackImpl(new ArrayList()));

		
		
		
		System.out.println("Tests Completed");
	
	}

	
	public void testReturnObjectImpl(ReturnObject testObject, Object object, ErrorMessage error, boolean result, String message){
		
		boolean hasErrorTestFail = false;
		boolean getReturnValueTestFail = false;
		boolean getErrorTestFail = false;
			
		
		//tests that the getError message will match to the intended message
		if (!error.equals(testObject.getError())){
			getErrorTestFail = true;
		}

		//tests whether the hasError is returning correctly
		if (result != testObject.hasError()){
			hasErrorTestFail = true;
		}
		
		//tests that the return object is returning correctly
		if ((object == null && testObject.getReturnValue() != null)||
			 object != null && !object.equals(testObject.getReturnValue())){
			getReturnValueTestFail = true;
		}
		
		
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
				//TODO - Add in messages for specific tests
		}
	}
	
	//Series of list tests and parts
	public void testList(String tag, List list){
		
		long startTime = System.nanoTime();
	
		ReturnObject returnedObject = new ReturnObjectImpl (null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		String currentTest = tag+ " isEmpty intial test";
	
		testListIsEmpty(list, true, currentTest);
		
		for (int i = 0; i < 9 ; i++)
			returnedObject = list.add(i);
		
		testListPart(list,  tag+" add (no index) 1"	, "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"		  , 10, list.add(9)			 , null, ErrorMessage.NO_ERROR);
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
		
		for (int i = 0; i <9 ; i++){
			list.remove(0);
		}

		testListIsEmpty(list, true, tag+" isEmpty test final");
		
		
		//TODO add test to remove full list and test if empty
		long endTime = System.nanoTime();
		System.out.println("###"+tag+" took "+(endTime - startTime) + " ns###");
		System.out.println();
		
		
	}
	
	
	//List testing elements, made of full list test and several parts
	void testListPart(List list, String currentTest, String endListState, int endListSize, ReturnObject function, Object returnValue, ErrorMessage returnError){
		
		Boolean hasError = true;
		
		if (returnError.equals(ErrorMessage.NO_ERROR))
			hasError = false;
		
		testListMatch(list,endListState, currentTest);
		testReturnObjectImpl(function, returnValue, returnError, hasError, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, endListSize);
		testListIsEmpty(list, false, currentTest);		
	}
	
	//Component list tests
	void testListSize(List list, String tag, int size){
		if (list.size() != size){
			System.out.println(tag+ "Size test failed");
			System.out.println(list.size() + " != "+size);
		}
	}
	
	void testListMatch(List list, String match, String test){
		if (!list.toString().equals(match)){
			System.out.println(test+" match test failed - output string does not match");
			System.out.println("Target: "+match);
			System.out.println("Actual: "+list);		
		}		
	}
	
	void testListIsEmpty(List list, boolean value, String test){
		if (!list.isEmpty() == value){
			System.out.println(test + " isEmpty test failed");
			System.out.println(list.isEmpty() + "!="+value);
		}
	
	}
	
	//Functional List tests - to be written once clarification has been recieved
	
	void testFunctionalList(String tag, FunctionalList list){
		testList(tag,list);	
		//TODO - write functional list tests
	}
	
	
	//SampleableList tests

	void testSampleableList(String tag, SampleableList list){
		testList(tag, list);

		for (int i = 0; i < 9; i++){
			list.add(i);
		}
		
		SampleableList newList = list.sample();
		if (!newList.toString().equals("[0:0, 1:2, 2:4, 3:6, 4:8]")){
			System.out.println(tag+ " sample test 1 failed: Sample doesn't match");
			System.out.println("Target: [0:1, 1:3, 2:5, 3:7, 4:9]");
			System.out.println("Actual: "+newList);
		}
		
		list.remove(1);
		if (!newList.toString().equals("[0:0, 1:2, 2:4, 3:6, 4:8]")){
			System.out.println(tag+ " sample test 2 failed: change to new list");
			System.out.println("Target: [0:1, 1:3, 2:5, 3:7, 4:9]");
			System.out.println("Actual: "+newList);
		}
			
		newList.remove(1);
		if (!list.toString().equals("[0:0, 1:2, 2:3, 3:4, 4:5, 5:6, 6:7, 7:8]")){
			System.out.println(tag+ " sample test 3 failed: change to old list");
			System.out.println("Target: [0:0, 1:2, 2:3, 3:4, 4:5, 5:6, 6:7, 7:8]");
			System.out.println("Actual: "+list);
		}	
	}
	
	//Stack test
	
	void testStack(String tag, Stack stack){
		long startTime = System.nanoTime();
		
		//TODO - add in a test to check the top of null stacks
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
	
	void testStackIsEmpty(String tag, Stack stack, boolean value){
		if (!stack.isEmpty() == value){
			System.out.println(tag + " isEmpty test failed");
			System.out.println("Target:" + value + "!="+stack.isEmpty() );
		}
	}

	void testStackPop(String tag, Stack stack, ReturnObject returnValue, ErrorMessage error, 
			Object returnedValue, boolean hasError, String match, int size, Object top, boolean isEmpty){
	
		testStackMatch(tag,stack,match,size, top);
		testReturnObjectImpl(returnValue, returnedValue, error, hasError, tag+"return object test failed");
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
			System.out.println("Actual: "+stack.top());			
		}

	}
}