package projectCode20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

	private class Node<E> {
		// node contains type initialized as and next node
		E data;
		DoublyLinkedList.Node next;
		DoublyLinkedList.Node prev;


		Node(E inp) {
			data = inp;
			next = null;
			prev = null;
		}
	}

	Node head;

	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node add = new Node(e);
		predecessor.next = add;
		successor.prev = add;
		add.next = successor;
		add.prev = predecessor;
	}
	
	@Override
	public int size() {
		int count = 0;
		Node cur = head;
		if (head == null) return 0;
		while(cur.next != null) {
			count++;
			cur = cur.next;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public E get(int i) {
		Node cur = head;
		for (int j = 0; j < i; j++) {
			cur = cur.next;
		}
		return (E)cur.data;
	}

	@Override
	public void add(int i, E e) {
		Node add = new Node(e);
		Node cur = head;
		for (int j = 0; j < i; j++) {
			cur = cur.next;
		}
		add.next = cur.next;
		add.prev = cur;
		cur.next = add;
	}

	@Override
	public E remove(int i) {
		Node cur = head;
		for (int x = 0; x < i; x++) {
			cur = cur.next;
		}
		E x = (E) cur.next.data;
		cur.next = cur.next.next;
		cur.next.prev = cur;
		return x;
	}

	@Override
	public Iterator<E> iterator() {

		return new ListIterator();
	}
	private class ListIterator  implements Iterator<E>
	{
		DoublyLinkedList.Node cur;

		public ListIterator()
		{
			cur = head;
		}

		public boolean hasNext()
		{
			if (cur == null) {
				return false;
			}
			return true;
		}

		public E next()
		{
			E res = (E) cur.data;
			cur = cur.next;
			return res;
		}
	}


	@Override
	public E removeFirst() {
		E x = (E) head.data;
		head = head.next;
		return x;
	}

	@Override
	public E removeLast() {
		Node cur = head;
		if (!isEmpty()) {
			while (cur.next != null) {
				cur = cur.next;
			}
			E x = (E) cur.data;
			cur.prev.next = null;
			return x;
		} else {
			return null;
		}

	}

	@Override
	public void addFirst(E e) {
		Node add = new Node(e);
		if (isEmpty()) {
			head = add;
		} else {
			add.next = head;
			head.prev = add;
			head = add;
		}
	}

	@Override
	public void addLast(E e) {
		Node add = new Node(e);
		if (isEmpty()) {
			addFirst(e);
		} else {
			Node cur = head;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = add;
			add.prev = cur;
		}
	}

	@Override
	public String toString() {
		String ret = "[";
		DoublyLinkedList.Node cur = head;
		while(cur.next != null) {
			ret += cur.data + ", ";
			cur = cur.next;
		}
		ret += cur.data + "]";
		return ret;
	}
	
	public static void main(String[] args) {
		   DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
           ll.addFirst(0);
           ll.addFirst(1);
           ll.addFirst(2);
           ll.addLast(-1);

           System.out.println(ll.toString());
           
           ll.removeFirst();
           System.out.println(ll.toString());

           ll.removeLast();
           System.out.println(ll.toString());
           
           for(Integer e: ll) {
                   System.out.println("value: " + e);
           }
	}

	
}
