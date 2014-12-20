
public class ReturnObjectImpl implements ReturnObject {

	private ErrorMessage error;
	private Object returnValue;
	
	//Must 
	public ReturnObjectImpl(Object entry, ErrorMessage error){
		this.returnValue = entry;
		this.error = error;
	}
		
	@Override
	public boolean hasError() {
		if (error == ErrorMessage.NO_ERROR) return false;
		else return true;
	}

	@Override
	public ErrorMessage getError() {
		return error;
	}

	@Override
	public Object getReturnValue() {
		if (this.hasError()){
			return null;
		}else{
			return returnValue;
		}
	}

}
