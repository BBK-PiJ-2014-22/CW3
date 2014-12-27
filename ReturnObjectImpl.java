
public class ReturnObjectImpl implements ReturnObject {

	private ErrorMessage error;
	public Object returnValue;
	
	//Must 
	public ReturnObjectImpl(Object entry, ErrorMessage error){
		this.returnValue = entry;
		this.error = error;
	}
	
	public ReturnObjectImpl(Object entry){
		this.returnValue = entry;
		this.error = ErrorMessage.NO_ERROR;
	}
	
	public ReturnObjectImpl(ErrorMessage error){
		this.returnValue = null;
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
	
	@Override
	public String toString(){
		return "[Return Value: "+this.returnValue+", Error Message: "+this.error+", Has Error: "+this.hasError()+"]";
	}

}
