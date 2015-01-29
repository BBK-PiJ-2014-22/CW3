package datastructures;

/**	{@inheritDoc} 
 * 
 * @author Jamie MacIver
 *
 */

public class FunctionalArrayList extends ArrayList implements FunctionalList {

	//Functional Array List methods
	/**{@inheritDoc} 
	 */
	@Override
	public ReturnObject head() {
		Object toReturn = array[0];
		if (toReturn == null)
			return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		else
			return new ReturnObjectImpl(toReturn);
	}

	/**{@inheritDoc} 
	 */
	@Override
	public FunctionalList rest() {
		
		FunctionalList result = new FunctionalArrayList();		
		for (int i = 1; i < this.size ; i ++)
			result.add(this.get(i).getReturnValue());
		
		return result;
	}

}
