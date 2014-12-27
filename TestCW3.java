
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

	}
	
	void testListPart(List list, String currentTest, String endListState, int endListSize, ReturnObject function, Object returnValue, ErrorMessage returnError){
		
		System.out.println("***Starting "+currentTest+"***");
		Boolean hasError = true;
		
		if (returnError.equals(ErrorMessage.NO_ERROR))
			hasError = false;
		
		testListMatch(list,endListState, currentTest);
		testReturnObjectImpl(function, returnValue, returnError, hasError, "Return Object for "+currentTest+" failed");
		testListSize(list, currentTest, endListSize);
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