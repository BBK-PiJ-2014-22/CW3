package datastructures;

/**	{@inheritDoc} 
 * 
 * @author Jamie MacIver
 *
 */

public class FunctionalLinkedList extends LinkedList implements FunctionalList {

	//Functional List methods
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject head() {
		if (head == null) 
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		else			return new ReturnObjectImpl(head.value);
	}

	@Override
	/**{@inheritDoc} 
	 */
	public FunctionalList rest() {
		if (head == null)
			return new FunctionalLinkedList();
		else if (head.next == null)
			return new FunctionalLinkedList();
		else{
			FunctionalList result = new FunctionalLinkedList();
			for (int i = 1; i < this.size() ; i++){
				result.add(this.get(i).getReturnValue());
			}
		return result;
		}
	}
	
}
