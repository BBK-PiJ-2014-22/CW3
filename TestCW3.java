
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

		
		ArrayList testarray = new ArrayList();
	
		System.out.println(testarray.add(null));
		System.out.println(testarray.add("foo"));
		System.out.println(testarray.size());
		System.out.println(testarray.add("bar"));
		System.out.println(testarray.size());
		
		System.out.println("Tests Complete");
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
	
	
	
}
