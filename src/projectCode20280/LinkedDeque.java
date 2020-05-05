package projectCode20280;

public class LinkedDeque<E> implements Deque<E> {

	DoublyLinkedList<E> deque = new DoublyLinkedList();

	@Override
	public int size() {
		return deque.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E first() {
		return deque.get(0);
	}

	@Override
	public E last() {
		return deque.get(deque.size()-1);
	}

	@Override
	public void addFirst(E e) {
		deque.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		deque.addLast(e);
	}

	@Override
	public E removeFirst() {
		return deque.removeFirst();
	}

	@Override
	public E removeLast() {
		return deque.removeLast();
	}

	@Override
	public String toString() {
		String ret = "[";
		for(E x : deque) {
			ret += x + ", ";
		}

		ret = ret.substring(0, ret.length()-2);
		ret += "]";

		return ret;
	}

}
