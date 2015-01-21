
public class ImprovedStackImpl implements ImprovedStack {

	Stack mainStack;
	
	public ImprovedStackImpl(List list){
		mainStack = new StackImpl(list);
	}
	
	public ImprovedStackImpl(){
		mainStack = new StackImpl(new ArrayList());
	}
	
	@Override
	public String toString(){
		return mainStack.toString();
	}
	
	@Override
	public boolean isEmpty() {
		return mainStack.isEmpty();
	}

	@Override
	public int size() {
		return mainStack.size();
	}

	@Override
	public void push(Object item) {
		mainStack.push(item);

	}

	@Override
	public ReturnObject top() {
		return mainStack.top();
	}

	@Override
	public ReturnObject pop() {
		return mainStack.pop();
	}

	@Override
	public ImprovedStack reverse() {
		List reverseList = this.getList();
		this.addList(reverseList);
		return new ImprovedStackImpl(reverseList);
	}
		

	@Override
	public void remove(Object object) {
		List reverseList = this.getList();
		List toRemove = new LinkedList();
		
		//builds a list of the indexes that need to be removed
		for (int i = 0; i < reverseList.size(); i++){
			if (reverseList.get(i).getReturnValue().equals(object)){
				toRemove.add(i);
			}
		}
		
		//removes the elements last to first (so that the indexes remain)
		for (int j = toRemove.size()-1; j >= 0 ; j--){
			reverseList.remove(j);
		}
		
		//Rebuilds the stack
		this.addList(reverseList);
	}
	
	/**Get's a list equivalent to the reverse of the stack by popping all elements of the stack.
	 * Will leave the Stack empty.
	 * 
	 * Used within the Reverse and Remove methods.
	 * 
	 * @return a list of elements in reverse order of the Stack
	 */
	private List getList(){
		
		List reverseList = new ArrayList();		
		while (!mainStack.isEmpty()){
			reverseList.add(mainStack.pop().getReturnValue());
		}
		return reverseList;
	}
	
	
	/**Pushes a list into the Improved Stack. The list will be pushed from end to start, so that 
	 * list element[0] will be element at the top of the stack.
	 * 
	 * Used in the Reverse and Remove methods
	 * 
	 * @param list that should be pushed to the stack
	 */
	private void addList(List list){
		
		for (int i = list.size()-1; i <= 0; i--){
		mainStack.push(list.get(i).getReturnValue());
		}

	}
}
