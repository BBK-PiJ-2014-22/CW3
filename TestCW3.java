
public class TestCW3 {

	public static void main(String[] args) {
		TestCW3 test = new TestCW3();
		test.launch();
	}
	
	public void launch(){
		
		
		
	}

	
	public void testReturnObjectImpl(Object object, ErrorMessage error, String message){
		
		
		boolean hasErrorTestFail = false;
		boolean getReturnValueTestFail = false;
		boolean getErrorTestFail = false;
		
		
		ReturnObject testObject = new ReturnObjectImpl(object, error);
		
		//tests that the getError message will match to the intended message
		if (error != testObject.getError()){
			getErrorTestFail = true;
		}

		//tests whether the hasError is returning correctly
		if (error != ErrorMessage.NO_ERROR){
			if (testObject.hasError() != false){
				hasErrorTestFail = true;
			}
		}else{
			if (testObject.hasError() != true){
				hasErrorTestFail = true;
			}
		}
		
		//tests that the return object is returning correctly
		if (error == ErrorMessage.NO_ERROR){
			if (!testObject.getReturnValue().equals(object)){
				getReturnValueTestFail = true;
			}else{
				if (testObject.getReturnValue() != null){
					getReturnValueTestFail = true;
				}
			}
		}
		
		//Prints messages if necessary
		if (getErrorTestFail || hasErrorTestFail || getReturnValueTestFail){
			System.out.println(message);
			//TODO - Add in messages for specific tests
		}
		
		
	}
	
	
	
}
