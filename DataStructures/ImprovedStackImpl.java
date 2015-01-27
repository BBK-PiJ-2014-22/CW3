package DataStructures;


public class ImprovedStackImpl implements ImprovedStack {

	AbstractStack mainStack;
	
	public ImprovedStackImpl(List list){
		mainStack = new StackImpl(list);
	}
	
	public ImprovedStackImpl(ImprovedStackImpl stack){
		this.mainStack = new StackImpl((AbstractStack)stack.mainStack);
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
		ImprovedStackImpl tempStack = new ImprovedStackImpl(this);
		ImprovedStack reverse = new ImprovedStackImpl(new ArrayList());
		
		while(!tempStack.isEmpty()){
			reverse.push(tempStack.pop().getReturnValue());
		}
		return reverse;
	}
		

	@Override
	public void remove(Object object) {
		
		List list = this.mainStack.internalList;
		
		for (int i = list.size()-1 ; i >= 0 ; i--){
			if (list.get(i).getReturnValue().equals(object)){
				list.remove(i);
			}
			
		}
		
		/*List newList = this.getList();
		List toRemove = new LinkedList();
		
		//builds a list of the indexes that need to be removed
		for (int i = 0; i < newList.size(); i++){
			if (newList.get(i).getReturnValue().equals(object)){
				toRemove.add(i);
			}
		}
		
		//removes the elements last to first (so that the indexes remain)
		for (int j = toRemove.size()-1; j >= 0 ; j--){
			newList.remove(j);
		}
		
		//Rebuilds the stack
		this.mainStack = new StackImpl(newList);*/
	}
	
	/**Get's a list equivalent to the reverse of the stack by popping all elements of the stack.
	 * Will leave the Stack empty.
	 * 
	 * Used within the Reverse and Remove methods.
	 * 
	 * @return a list of elements in reverse order of the Stack
	 */
	private List getList(){
		
		ImprovedStackImpl copy = new ImprovedStackImpl(this);
		
		List reverseList = new ArrayList();		
		while (!copy.mainStack.isEmpty()){
			reverseList.add(copy.mainStack.pop().getReturnValue());
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
