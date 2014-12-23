
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
		// TODO Auto-generated method stub
		return null;
	}

	public ReturnObject remove(int index) {
		// TODO Auto-generated method stub
		return null;
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
	private void shuffleRight(){
		
	}
	
	//Moves all nodes 1 position to the left
	private void shuffleLeft(){
		
	}
}
