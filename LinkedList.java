
public class LinkedList implements List {

	LinkedListNode head;
	
	public LinkedList(){
		head = null;
	}
	
	
	@Override
	public boolean isEmpty() {
		if (head == null) 
			return false;
		else
			return true;
	}

	@Override
	public int size() {
		if (head == null)
			return 0;
		else 
			return head.size();
	}

	@Override
	public ReturnObject get(int index) {
		if (head == null){
			return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}else{
			return head.get(index);
		}
	}

	@Override
	public ReturnObject remove(int index) {
		if (head == null){
			return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}else if (index == 0){
			LinkedListNode removed = head;
			head = head.next;
			head.shuffleRight();
			return new ReturnObjectImpl(removed.value,ErrorMessage.NO_ERROR);
		}
		else{
			return head.remove(index);
		}
	}

	@Override
	public ReturnObject add(int index, Object item) {
		if (head == null){
			return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}else{
			return head.add(index, item);
		}
	}

	@Override
	public ReturnObject add(Object item) {
		if (head == null){
			head = new LinkedListNode(0, item);
			return new ReturnObjectImpl(null, ErrorMessage.NO_ERROR);
		}else{
			return head.add(item);
		}
	}
}