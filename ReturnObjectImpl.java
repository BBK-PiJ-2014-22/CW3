
public class ReturnObjectImpl implements ReturnObject {

	private ErrorMessage error;
	private Object returnValue;
	
	public ReturnObjectImpl(Object entry){
		this.returnValue = entry;
		this.error = null;
	}
	
	public ReturnObjectImpl(Object entry, ErrorMessage error){
		this.returnValue = entry;
		this.error = error;
	}
	
	
	@Override
	public boolean hasError() {
		if (this.error == null) return false;
		else return true;
	}

	@Override
	public ErrorMessage getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getReturnValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
