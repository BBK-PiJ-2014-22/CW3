package datastructures;


/**Implements the List interface using linked Nodes containing values
 *
 *@author Jamie MacIver
 */

public class LinkedList implements List {

	LinkedListNode head;
	
	//Constructors
	/**Default constructor. Creates an empty list.
	 */
	public LinkedList(){
		head = null;
	}

	/**Copy constructor.Creates a copy of the list.
	 * 
	 * @param list List to be copied.
	 */
	public LinkedList(List list){
		head = null;
		if (list.size() != 0){
			for (int i = 0; i <= list.size(); i++)
				this.add(list.get(i).getReturnValue());
		}
	}
	
	//Standard methods from Object
	@Override
	/**Returns a string representation [index1:value1, index2:value2 ...]
	 */
	public String toString(){
		return "["+head+"]";
	}
	
	/**Returns true if all elements of the lists have the same value at each index
	 */
	@Override
	public boolean equals(Object object){
		if (!(object instanceof List)) return false;
		
		List list = (List) object;
		
		if (this.size() != list.size())
			return false;
		else{
			for (int i = 0; i < this.size() ; i++){
				if (!this.get(i).equals(list.get(i))){
					return false;
				}
			}
		}
		return true;
	}
	
	//List Interface Methods
	/**{@inheritDoc} 
	 */
	@Override
	public boolean isEmpty() {
		if (head == null) 
			return true;
		else
			return false;
	}

	/**{@inheritDoc} 
	 */
	@Override
	public int size() {
		if (head == null)
			return 0;
		else 
			return head.size();
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject get(int index) {
		if (head == null){
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}else{
			return head.get(index);
		}
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject remove(int index) {
		if (head == null){
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}else if (index == 0){
			LinkedListNode removed = head;
		
			head = head.next;
			if (head != null){
				head.shuffleLeft();
			}
			return new ReturnObjectImpl(removed.value);
		}
		else
			return head.remove(index);
	}

	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject add(int index, Object item) {
		if (head == null){
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}else{
			return head.add(index, item);
		}
	}

	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject add(Object item) {
		if (head == null){
			head = new LinkedListNode(0, item);
			return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
		}else{
			return head.add(item);
		}
	}
}