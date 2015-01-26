	//TODO - refactor so that ReturnObjects use the simplified constructors

public class ArrayList implements List {
	
	protected Object[] array;
	protected int size;
	
	public ArrayList(){
		array = new Object[1];
		size = 0;
	}
	
	public ArrayList(List list){
		this.array = new Object[list.size()+1];
		this.size = 0;
		for (int i = 0; i <= list.size(); i++){
			this.add(list.get(i).getReturnValue());
		}
	}
	
	@Override
	public String toString(){
		
	
		
		String result = "[";
		
		if (size > 0){

			for (int i = 0; i < this.size-1 ; i++){
				result += i +":"+array[i]+", ";
			}
			result += size-1+":"+array[size-1];
		}else{
			result += array[0];
		}
		result += "]";
		return result;
	}
	
	@Override
	//If any element of the list is filled, the first element will be filled
	public boolean isEmpty() {
		if (this.array[0] == null) 
			return true;
		else 
			return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ReturnObject get(int index) {
		if (checkOutOfBounds(index)){
			return outOfBoundsError();
		}else{
			return new ReturnObjectImpl(array[index], ErrorMessage.NO_ERROR);
		}
	}

	@Override
	public ReturnObject remove(int index) {
		if (checkOutOfBounds(index)){
			return outOfBoundsError();
		}else{
			Object toReturn = this.array[index];
			int shuffle = index+1;
			while(this.array[shuffle] != null){
				this.array[shuffle-1] = this.array[shuffle];
				shuffle++;
			}
			
			size --;
			if (size == 0){
				array[0] = null;
			}
			return new ReturnObjectImpl(toReturn, ErrorMessage.NO_ERROR);		
		}
	}

	@Override
	public ReturnObject add(int index, Object item) {
		if (item == null){
			return new ReturnObjectImpl(item, ErrorMessage.INVALID_ARGUMENT);
		}else if (checkOutOfBounds(index)){
			return outOfBoundsError();
		}else{
			this.checkSize();
			int shuffle = size;
			do{
				this.array[shuffle] = this.array[shuffle-1];
				shuffle = shuffle-1;
			}while(!(shuffle <= index));
			
			this.array[index] = item;
			this.size ++;
			return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);	
		}
	}

	@Override
	public ReturnObject add(Object item) {
		if (item == null){
			return new ReturnObjectImpl(item, ErrorMessage.INVALID_ARGUMENT);
		}else{
			this.checkSize();
			this.array[size] = item;
			this.size ++;
			return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);
		}
	}
	
	/**Checks the size of the current array before trying to add anything
	 * If the size is equal to the max size of the current array, the array 
	 * size will double and all existing elements be copied to the new array
	 */
	private void checkSize(){
		if (this.size == array.length){
			Object[] newArray = new Object[size*2];
			for (int i = 0; i < size ; i++){
				newArray[i] = array[i];
			}
			this.array = newArray;
		}
	}
	
	/** Tests if an index is out of bounds. Used in most methods*/
	private boolean checkOutOfBounds(int index){
		if (index >= this.size || index < 0){
			return true;
		}else{
			return false;
		}
	}
	
	/** Return object for when an index is out of bounds*/
	private ReturnObjectImpl outOfBoundsError(){
		return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
	}
	
	
}
