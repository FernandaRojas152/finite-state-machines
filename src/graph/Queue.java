package graph;

import java.util.Collection;
import java.util.LinkedList;

/**
 * The data type that this queue will use.
 * @author W-7
 *
 * @param <T>The data type that this queue will use.
 */
public class Queue<T> implements ICollection<T>{

	/**
	 * The representative Linked List (LL) of this queue.
	 */
	private LinkedList<T> rep;
	
	public Queue() {
		rep = new LinkedList<>();
	}
	
	/**
	 * Adds an element in the tail (last position of the representative LL) of this queue.
	 */
	@Override
	public void add(T element) {
		rep.addLast(element);
	}

	@Override
	public T poll() {
		return rep.removeFirst();
	}

	@Override
	public T peek() {
		return rep.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return rep.isEmpty();
	}

	@Override
	public void addAll(Collection<T> c) {
		rep.addAll(c);
	}

}
