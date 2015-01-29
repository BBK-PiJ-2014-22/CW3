package datastructures;

/**Nodes for use with the LinkedList implementation of List
 * 
 * @author Jamie MacIver
 *
 */
public class LinkedListNode {

	Object value;
	int position;
    LinkedListNode next;
	
	//Constructors
    /**Standard constructor which adds a given node to a specific position
     * 
     * @param position Index in the list for the node
     * @param value Value which the node will hold
     */
	public LinkedListNode(int position, Object value){
		this.value = value;
		this.position = position;
		this.next = null;
	}
	
	//Standard Methods from Object
	/**String representation of node position:value
	 */
	@Override
	public String toString(){
		if (this.next == null){
			return this.position +":"+this.value.toString();
		}else{
			return this.position+":"+this.value.toString()+", "+this.next.toString();
		}
	}
	
	//List methods
	/**Returns the size of the list
	 * 
	 * @return size of list
	 */
	public int size(){ 
		if (this.next == null)
			return this.position+1;
		else
			return this.next.size();
	}

	/**Returns a ReturnObject for a specific index in the list 
	 * 
	 * @param index Index which value should be returned
	 * @return ReturnObject with value of specific index, or error message if out of bounds
	 */
	public ReturnObject get(int index) {
		if (this.position == index)
			return new ReturnObjectImpl(this.value);
		else if (this.next != null)
			return this.next.get(index);
		else
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
	}

	/**Removes element at index and returns a ReturnObject with the value at that index or error message if out of bounds
	 * 
	 * @param index Index which value should be removed
	 * @return ReturnObject with value of specific index, or error message if out of bounds
	 */
	public ReturnObject remove(int index) {
		if (this.next == null)
			return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		else if(this.next.position == index){
			LinkedListNode removed = this.next;
			this.next = this.next.next;
			if (this.next != null)
				this.next.shuffleLeft();
			return new ReturnObjectImpl(removed.value, ErrorMessage.NO_ERROR);
		}else
			return this.next.remove(index);
	}

	/**Insterts an element into a particular index in the list
	 * 
	 * @param index Point to insert value into the list
	 * @param item Value to be added to the list
 	 * @return Error message of NO_ERROR or INDEX_OUT_OF_BOUNDS
	 */
	public ReturnObject add(int index, Object item) {
		if (item == null)
			return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		else if (this.next == null)
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		else if (this.next.position == index){
			LinkedListNode newNode = new LinkedListNode(this.position+1, item);
			newNode.next = this.next;
			this.next = newNode;
			newNode.next.shuffleRight();
			return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
		}else
			return this.next.add(index, item);			
	}
	
	/**Adds an item to the end of the list
	 * 
	 * @param item Object to be added to the list
	 * @return ReturnObject with error message of NO_ERROR or INVALID_ARGUMENT if a null element is added
	 */

	public ReturnObject add(Object item) {
		if (item == null){
			return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);
		}else if (this.next == null){
			this.next = new LinkedListNode(this.position+1, item);
			return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);
		}else
			return this.next.add(item);
	}
	
	/**Shuffles all nodes 1 position to the right. Used as part of insert method
	 */
	void shuffleRight(){
		this.position ++;
		if (this.next != null){
			this.next.shuffleRight();
		}
	}
	
	/**Shuffles all nodes 1 position to the left. Used as part of remove method
	 */
	void shuffleLeft(){
		this.position --;
		if (this.next != null){
			this.next.shuffleLeft();
		}
	}
}
