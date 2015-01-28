package datastructures;


public class ImprovedStackImpl implements ImprovedStack, Stack {

	AbstractStack mainStack;
	
	
	//Constructors
	/**Constructs a stack using a list as an input. Can be an empty or a populated list.
	 * A populated list will have the last element as the top 
	 * 
	 * @param list List implementation to be used, empty or populated. 
	 */
	public ImprovedStackImpl(List list){
		mainStack = new StackImpl(list);
	}
	
	/**Copy constructor, creating a copy of the stack
	 * 
	 * @param stack Stack to be copied
	 */
	public ImprovedStackImpl(ImprovedStackImpl stack){
		this.mainStack = new StackImpl((AbstractStack)stack.mainStack);
	}
	
	/**Default constructor if no specific list is provided.	 * 
	 */
	public ImprovedStackImpl(){
		mainStack = new StackImpl(new ArrayList());
	}
	
	//Standard methods inherited from Object
	/**Returns a string representation [index1:value1, index2:value2 ...]
	 */
	@Override
	public String toString(){
		return mainStack.toString();
	}
	
	@Override
	public boolean equals(Object object){
		return mainStack.equals(object);
	}

	//Stack methods
	/**{@inheritDoc} 
	 */
	@Override
	public boolean isEmpty() {
		return mainStack.isEmpty();
	}

	/**{@inheritDoc} 
	 */
	@Override
	public int size() {
		return mainStack.size();
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public void push(Object item) {
		mainStack.push(item);

	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject top() {
		return mainStack.top();
	}
	
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject pop() {
		return mainStack.pop();
	}
	
	//Improved stack methods
	/**{@inheritDoc} 
	 */
	@Override
	public ImprovedStack reverse() {
		ImprovedStackImpl tempStack = new ImprovedStackImpl(this);
		ImprovedStack reverse = new ImprovedStackImpl(new ArrayList());
		
		while(!tempStack.isEmpty())
			reverse.push(tempStack.pop().getReturnValue());
		return reverse;
	}

	/**{@inheritDoc} 
	 */
	@Override
	public void remove(Object object) {
		
		List list = this.mainStack.internalList;
		
		for (int i = list.size()-1 ; i >= 0 ; i--){
			if (list.get(i).getReturnValue().equals(object))
				list.remove(i);
		}
	}
}
