package projectCode20280;

public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> queue = new SinglyLinkedList<>();

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		queue.addLast(e);
	}

	@Override
	public E first() {
		return queue.get(0);
	}

	@Override
	public E dequeue() {
		return queue.removeFirst();
	}

	@Override
	public String toString() {
		String ret = "[";
		for(E x : queue) {
			ret += x + ", ";
		}

		ret = ret.substring(0, ret.length()-2);
		ret += "]";

		return ret;
	}

}
