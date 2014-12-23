
public class ArrayList implements List {
	
	public Object[] array;
	public int size;
	
	public ArrayList(){
		array = new Object[1];
		size = 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnObject remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnObject add(int index, Object item) {
		if (item == null){
			return new ReturnObjectImpl(item, ErrorMessage.INVALID_ARGUMENT);
		}else if (index >= this.size || index < 0){
			return new ReturnObjectImpl(item, ErrorMessage.INDEX_OUT_OF_BOUNDS);
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
	
}
