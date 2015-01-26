
public class StackImpl extends AbstractStack {

	public StackImpl(List list){
		super(list);
	}
	
	public StackImpl(AbstractStack stack){
		super(new ArrayList(stack.internalList));
	}
	
	@Override
	public String toString(){
		return internalList.toString();
	}
	
	@Override
	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	@Override
	public int size() {
		return internalList.size();
	}

	@Override
	public void push(Object item) {
		internalList.add(item);
	}

	@Override
	public ReturnObject top() {
		if (this.isEmpty()){
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}else{
		return internalList.get(internalList.size()-1);
		}
	}

	@Override
	public ReturnObject pop() {
		if (this.isEmpty()){
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}else{
		return internalList.remove(internalList.size()-1);
		}
	}
}