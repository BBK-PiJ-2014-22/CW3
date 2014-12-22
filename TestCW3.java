
public class TestCW3 {

	public static void main(String[] args) {
		TestCW3 test = new TestCW3();
		test.launch();
	}
	
	public void launch(){
		
		testReturnObjectImpl(new ReturnObjectImpl("String", ErrorMessage.NO_ERROR),"String", ErrorMessage.NO_ERROR, false, "Testing test failure");
	
		
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
		if ((object == null && testObject.getReturnValue() != object)||
			 !object.equals(testObject.getReturnValue())){
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
