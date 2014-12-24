
public class ArrayList implements List {
	
	private Object[] array;
	private int size;
	
	public ArrayList(){
		array = new Object[1];
		size = 0;
	}
	
	@Override
	public String toString(){
		
		String result = "[";
	
		for (int i = 0; i < this.size ; i++){
			result += i +":"+array[i]+", ";
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
			do{
				this.array[shuffle-1] = this.array[shuffle];
				shuffle++;
			}while (this.array[shuffle] != null);
			
			size --;
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
