package datastructures;
/**{@inheritDoc} 
 * 
 * @author Jamie MacIver
 *
 */

public class SampleableListImpl extends LinkedList implements SampleableList {

	/**{@inheritDoc} 
	 */
	@Override
	public SampleableList sample() {
		
		SampleableList returnList = new SampleableListImpl();
		
		if (this.head != null){
			LinkedListNode element = this.head;
			returnList.add(element.value);
			
			while (element.next != null){
				element = element.next;
				if (element.position%2 == 0){
					returnList.add(element.value);
				}
			}	
		}
		return returnList;
	}
}
