package datastructures;

//Nodes for implementation of Lists using linked nodes. See also LinkedList

public class LinkedListNode {

	Object value;
	int position;
    LinkedListNode next;
	
	//Constructors
	public LinkedListNode(int position, Object value){
		this.value = value;
		this.position = position;
		this.next = null;
	}
	
	//Standard Methods from Object
	@Override
	public String toString(){
		if (this.next == null){
			return this.position +":"+this.value.toString();
		}else{
			return this.position+":"+this.value.toString()+", "+this.next.toString();
		}
	}
	
	//List methods
	public int size(){ 
		if (this.next == null)
			return this.position+1;
		else
			return this.next.size();
	}

	public ReturnObject get(int index) {
		if (this.position == index)
			return new ReturnObjectImpl(this.value);
		else if (this.next != null)
			return this.next.get(index);
		else
			return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
	}

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

	public ReturnObject add(Object item) {
		if (item == null){
			return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);
		}else if (this.next == null){
			this.next = new LinkedListNode(this.position+1, item);
			return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);
		}else
			return this.next.add(item);
	}
	
	//Moves all nodes 1 position to the right
	void shuffleRight(){
		this.position ++;
		if (this.next != null){
			this.next.shuffleRight();
		}
	}
	
	//Moves all nodes 1 position to the left
	void shuffleLeft(){
		this.position --;
		if (this.next != null){
			this.next.shuffleLeft();
		}
	}
}
