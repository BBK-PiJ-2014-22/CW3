
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
		
		ReturnObject returnedObject = new ReturnObjectImpl (null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		String match;
		String currentTest = tag+ "isEmpty intial test";
		
		testListIsEmpty(list, true, currentTest);
	
		currentTest = tag+" add (no index) 1";
		for (int i = 0; i < 10 ; i++)
			returnedObject = list.add(i);
		testListMatch(list,"[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.NO_ERROR, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 10);
		testListIsEmpty(list, false, currentTest);
		
		currentTest = tag+" add (no index) 2";
		returnedObject = list.add(null);
		testListMatch(list,"[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INVALID_ARGUMENT, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 10);
		testListIsEmpty(list, false, currentTest);
			
		currentTest = tag+" add (index) 1";
		returnedObject = list.add(2, "two");
		testListMatch(list,"[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.NO_ERROR, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 11);
		testListIsEmpty(list, false, currentTest);

		currentTest = tag+" add (index) 2";
		returnedObject = list.add(12, "twelve");
		testListMatch(list,"[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 11);
		testListIsEmpty(list, false, currentTest);	

		currentTest = tag+" add (index) 3";
		returnedObject = list.add(-1, "minus one");
		testListMatch(list,"[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 11);
		testListIsEmpty(list, false, currentTest);

		
		//TODO - make this a null insert
		currentTest = tag+" add (index) 3";
		returnedObject = list.add(-1, "minus one");
		testListMatch(list,"[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 11);
		testListIsEmpty(list, false, currentTest);

		//TODO - make this a null and out of bounds insert
		currentTest = tag+" add (index) 3";
		returnedObject = list.add(-1, "minus one");
		testListMatch(list,"[0:0, 1:1, 2:two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 11);
		testListIsEmpty(list, false, currentTest);

		
		currentTest = tag+" remove test 1";
		returnedObject = list.remove(2);
		testListMatch(list,"[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", currentTest);
		testReturnObjectImpl(returnedObject, 2, ErrorMessage.NO_ERROR, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 10);
		testListIsEmpty(list, false, currentTest);	

		currentTest = tag+" remove test 2";
		returnedObject = list.remove(-1);
		testListMatch(list,"[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 10);
		testListIsEmpty(list, false, currentTest);	
		
		currentTest = tag+" remove test 3";
		returnedObject = list.remove(12);
		testListMatch(list,"[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", currentTest);
		testReturnObjectImpl(returnedObject, null, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 10);
		testListIsEmpty(list, false, currentTest);	
	
		currentTest = tag+" get test 1";
		returnedObject = list.get(3);
		testListMatch(list,"[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", currentTest);
		testReturnObjectImpl(returnedObject, 3, ErrorMessage.INDEX_OUT_OF_BOUNDS, false, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, 10);
		testListIsEmpty(list, false, currentTest);	

		
	}
	
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
}