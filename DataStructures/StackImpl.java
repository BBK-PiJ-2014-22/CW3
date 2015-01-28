package datastructures;

public class StackImpl extends AbstractStack {

	//Constructors
	/**Standard constructor. Takes a list and creates a stack using that list
	 * Can be empty or populated. If populated, the last element will be the top. 
	 * 
	 * @param list 
	 */
	public StackImpl(List list){
		super(list);
	}
	
	public StackImpl(AbstractStack stack){
		super(new ArrayList(stack.internalList));
	}
	
	//Standard methods from Object
	@Override
	public String toString(){
		return internalList.toString();
	}

	//TODO - Write equals()
	
	//Stack methods
	/**{@inheritDoc} 
	 */
	@Override
	public int size() {
		return internalList.size();
	}
	/**{@inheritDoc} 
	 */
	@Override
	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	/**{@inheritDoc} 
	 */
	@Override
	public void push(Object item) {
		internalList.add(item);
	}

	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject top() {
		if (this.isEmpty())
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		else
			return internalList.get(internalList.size()-1);
	}

	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject pop() {
		if (this.isEmpty()){
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}else{
		return internalList.remove(internalList.size()-1);
		}
	}
}