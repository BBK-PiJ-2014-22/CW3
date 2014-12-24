
public class LinkedListNode {

	Object value;
	int position;
	LinkedListNode next;
	
	public LinkedListNode(int position, Object value){
		this.value = value;
		this.position = position;
		this.next = null;
	}
	
	public int size(){ 
		if (this.next == null){
			return this.position+1;
		}else{
			return this.next.size();
		}
	}

	public ReturnObject get(int index) {
		if (this.position == index){
			return new ReturnObjectImpl(this.value, ErrorMessage.NO_ERROR);
		}else if (this.next != null){
			return this.next.get(index);
		}else{
			return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}
	}

	public ReturnObject remove(int index) {
		if (this.next.position == index){
			LinkedListNode removed = this.next;
			this.next = this.next.next;
			this.next.shuffleRight();
			return new ReturnObjectImpl(removed.value, ErrorMessage.NO_ERROR);
		}else{
			return this.next.remove(index);
		}
	}

	public ReturnObject add(int index, Object item) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReturnObject add(Object item) {
		// TODO Auto-generated method stub
		return null;
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
