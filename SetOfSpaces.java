package raymond_li;
/**
 * Provides an interface for the stack (SpaceStack) and queue (SpaceQueue)
 * @author Raymond Li
 *@version 02/28/14
 */
public interface SetOfSpaces {
	
	void add ( Space s);
	
	Space remove();
	
	boolean isEmpty();

}
