package raymond_li;

import java.util.NoSuchElementException;

/**
 * Class that creates the queue
 * @author Raymond Li
 * @version 02/28/14
 *
 */
public class SpaceQueue implements SetOfSpaces{
	
	private Space[] queue;
	private int N = 0;
	private int first = 0;
	private int last = 0;
	private final int DEFAULT_CAPACITY = 100;
	
	@Override
	/**
	 * Adds a space to the queue
	 * If capacity of queue has been reached, double the size of the array and recopy contents to front of queue
	 */
	public void add(Space s) {
        if (N == queue.length) expandCapacity(2*queue.length);   // double size of array if queue capacity not reached
        queue[last++] = s;								// add Space object to the back of the queue                        
        if (last == queue.length) last = 0;	          
        N++;
    }

	/**
	 * Removes space object from front of queue.
	 */
	public Space remove() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Space item = queue[first];
        queue[first] = null;                            
        N--;
        first++;
        if (first == queue.length) first = 0;           
        return item;
    }

	@Override
	/**
	 * checks if queue is empty
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return N == 0;
	}
	/**
	 * constructs queue of default capacity
	 */
	public SpaceQueue() {
        queue = new Space[DEFAULT_CAPACITY];
    }
	
	/**
	 * @return
	 * 		Returns size of array
	 */		
	public int size() {
        return N;
    }
	/**
	 * resizes array array
	 * @param max
	 * 		contains number of indexes within queue
	 */
	private void expandCapacity(int max) {
        Space[] temp = new Space[max];
        for (int i = 0; i < N; i++) {
            temp[i] = queue[(first + i) % queue.length];
        }
        queue = temp;
        first = 0;
        last  = N;
    }	

}
