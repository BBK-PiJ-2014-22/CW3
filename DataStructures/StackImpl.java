package datastructures;

/**{@inheritDoc} 
 * 
 * @author Jamie MacIver
 *
 */

public class StackImpl extends AbstractStack {

	//Constructors
	/**Standard constructor. Takes a list and creates a stack using that list
	 * Can be empty or populated. If populated, the last element will be the top. 
	 * 
	 * @param list List to use in Stack. If populated, the last element will be the top
	 */
	public StackImpl(List list){
		super(list);
	}
	
	/**Copy constructors. Takes an AbstractStack and creates a copy of that stack
	 * 
	 * @param stack AbstractStack to create a copy of
	 */
	public StackImpl(AbstractStack stack){
		super(new ArrayList(stack.internalList));
	}
	
	/**Copy constructor. Takes an ImprovedStack and creates a copy of that stack. 
	 * 
	 * @param stack Stack to create a copy of
	 */
	public StackImpl(ImprovedStackImpl stack){
		super(new ArrayList(stack.mainStack.internalList));
	}
	
	/**Default constructor. Creates a stack using an array list.
	 */
	public StackImpl(){
		super(new ArrayList());
	}

	
	//Standard methods from Object
	/**Returns a string representation [index1:value1, index2:value2 ...] with top as final element
	 */
	@Override
	public String toString(){
		return internalList.toString();
	}

	/**Returns true if object is an implementation of AbstractStack or ImprovedStackImpl and all elements are identical
	 * 
	 * @param object Object to compare to
	 */
	@Override
	public boolean equals(Object object){
		if ((object instanceof AbstractStack)){
			AbstractStack asObject = (AbstractStack)object;
			return internalList.equals(asObject.internalList);
		}else if (object instanceof ImprovedStackImpl){
			ImprovedStackImpl isObject = (ImprovedStackImpl)object;
			return internalList.equals(isObject.mainStack.internalList);	
		}else{
			return false;
		}
	}
	
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