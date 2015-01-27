package datastructures;


public class ImprovedStackImpl implements ImprovedStack, Stack {

	AbstractStack mainStack;
	
	
	//Constructors
	public ImprovedStackImpl(List list){
		mainStack = new StackImpl(list);
	}
	
	public ImprovedStackImpl(ImprovedStackImpl stack){
		this.mainStack = new StackImpl((AbstractStack)stack.mainStack);
	}
	
	public ImprovedStackImpl(){
		mainStack = new StackImpl(new ArrayList());
	}
	
	//Standard methods inherited from Object
	@Override
	public String toString(){
		return mainStack.toString();
	}
	
	@Override
	public boolean isEmpty() {
		return mainStack.isEmpty();
	}

	//Stack methods
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
	
	//Improved stack methods
	@Override
	public ImprovedStack reverse() {
		ImprovedStackImpl tempStack = new ImprovedStackImpl(this);
		ImprovedStack reverse = new ImprovedStackImpl(new ArrayList());
		
		while(!tempStack.isEmpty())
			reverse.push(tempStack.pop().getReturnValue());
		return reverse;
	}

	@Override
	public void remove(Object object) {
		
		List list = this.mainStack.internalList;
		
		for (int i = list.size()-1 ; i >= 0 ; i--){
			if (list.get(i).getReturnValue().equals(object))
				list.remove(i);
		}
	}

}
