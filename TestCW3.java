
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

		testList("Dummy List", new DummyList());
		testList("Array List", new ArrayList());
		testList("Linked List", new LinkedList());		
	
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
	
	
	public void testList(String tag, List list){
		
		ReturnObject testReturnObject = new ReturnObjectImpl (null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		
		
		//Builds a list of numbers 0 to 9
		for (int i = 0; i < 10 ; i++)
			testReturnObject = list.add(i);
		
		if (!list.toString().equals("[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]"))
			System.out.println(tag+" add (no index) test 1 failed - output string does not match");
		
		if (testReturnObject.getReturnValue() != null || 
			testReturnObject.getError() != ErrorMessage.NO_ERROR){
			System.out.println(tag+" add (no index) test 1 failed - return object incorrect. Displaying:");
			System.out.println(testReturnObject);
		}
			
		testListSize(list, tag, 10, 1);
		
		testReturnObject = list.add(2, "two");
		
		if (!list.toString().equals("[0:0, 1:1, 2:Two, 3:2, 4:3, 5:4, 6:5, 7:6, 8:7, 9:8, 10:9]"))
			System.out.println(tag+" add (index) test 1 failed");

		
		
		
		
		
	}
	
	void testListSize(List list, String tag, int size, int testnum){
		if (list.size() != size){
			System.out.println(tag+"size test "+testnum+" failed");
			System.out.println(list.size() + " != "+size);
		}
	}
}