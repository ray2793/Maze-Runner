package raymond_li;

/**
 * Class that creates the stack 
 * @author Raymond Li
 * @version 02/28/14
 *
 */
public class SpaceStack implements SetOfSpaces {

	private final int DEFAULT_CAPACITY = 100;
	private int top;		// represents top of the stack
	private Space[] stack;	

	public SpaceStack() {
		top = 0;
		stack = new Space[DEFAULT_CAPACITY];
	}
	/**
	 * creates stack with a specified capacity
	 * @param initialCapacity
	 */
	public SpaceStack(int initialCapacity) {
		top = 0;
		stack = new Space[initialCapacity];
	}

	@Override
	/**
	 * Adds to the top of the stack
	 */
	public void add(Space s) {
		if (size() == stack.length)
			expandCapacity();
		stack[top] = s;
		top++;
	}

	@Override
	/**
	 * Removes one from the top of the stack
	 */
	public Space remove() throws IndexOutOfBoundsException {
		if (isEmpty())
			throw new IndexOutOfBoundsException();
		--top;
		Space element = stack[top];
		stack[top] = null;
		return element;
	}

	@Override
	/**
	 * Checks if stack is empty
	 */
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		else
			return false;
	}
	/**
	 * Returns size of the stack
	 * @return
	 */
	public int size() {
		return top;
	}
	/**
	 * expands stack by doubling
	 */
	public void expandCapacity() {
		Space[] larger = (new Space[stack.length * 2]);
		for (int i = 0; i < stack.length; i++) {
			larger[i] = stack[i];
		}
		stack = larger;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int size = size();
		for (int i = 0; i < size; i++) {
			sb.append(stack[i]);
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

}
