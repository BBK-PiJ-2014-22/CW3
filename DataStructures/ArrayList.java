package datastructures;

/**{@inheritDoc} 
 * 
 * Implements the List interface using arrays
 * 
 * @author Jamie MacIver
 */

public class ArrayList implements List {
	
	Object[] array;
	int size;
	
	
	//Constructors
	/**Default constructor. Creates an empty list.
	 */
	public ArrayList(){
		array = new Object[1];
		size = 0;
	}
	
	/**Copy constructor.Creates a copy of the list.
	 * 
	 * @param list List to be copied.
	 */
	public ArrayList(List list){
		this.array = new Object[list.size()+1];
		this.size = 0;
		for (int i = 0; i <= list.size(); i++){
			this.add(list.get(i).getReturnValue());
		}
	}
	
	//Standard methods from Object
	@Override
	/**Returns true if all elements of the lists have the same value at each index
	 */
	public boolean equals(Object object){
		if (!(object instanceof List)) return false;
		
		List list = (List) object;	
		if (this.size() != list.size())
			return false;
		else{
			for (int i = 0; i < this.size() ; i++){
				if (!this.get(i).equals(list.get(i)))
					return false;
			}
		}
		return true;
	}
	
	@Override
	/**Returns a string representation [index1:value1, index2:value2 ...]
	 */
	public String toString(){
		
		String result = "[";
		if (size > 0){
			for (int i = 0; i < this.size-1 ; i++)
				result += i +":"+array[i]+", ";
			result += size-1+":"+array[size-1];
		}else
			result += array[0];
		result += "]";
		return result;
	}
	
	//List methods
	/**{@inheritDoc} 
	 */
	@Override
	public boolean isEmpty() {
		if (this.array[0] == null) 
			return true;
		else 
			return false;
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public int size() {
		return size;
	}

	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject get(int index) {
		if (this.array[0]==null)
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		else if (checkOutOfBounds(index))
			return outOfBoundsError();
		else
			return new ReturnObjectImpl(array[index]);
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject remove(int index) {
		if (this.array[0]==null)
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		else if (checkOutOfBounds(index))
			return outOfBoundsError();
		else{
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
			return new ReturnObjectImpl(toReturn);		
		}
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject add(int index, Object item) {
		if (item == null){
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
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

	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject add(Object item) {
		if (item == null){
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		}else{
			this.checkSize();
			this.array[size] = item;
			this.size ++;
			return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
		}
	}
	
	/**Checks the size of the current array before trying to add anything
	 * If the size is equal to the max size of the current array, the array 
	 * size will increase by 50 and all existing elements be copied to the new array
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
