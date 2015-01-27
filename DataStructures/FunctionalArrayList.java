package DataStructures;


public class FunctionalArrayList extends ArrayList implements FunctionalList {

	//TODO - await clarification 
	
	@Override
	public ReturnObject head() {
		Object toReturn = array[0];
		if (toReturn == null){
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}else{
			return new ReturnObjectImpl(toReturn);
		}
	}

	@Override
	public FunctionalList rest() {
		// TODO Auto-generated method stub
		return null;
	}

}
