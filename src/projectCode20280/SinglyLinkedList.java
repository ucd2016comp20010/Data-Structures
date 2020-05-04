package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

	private class Node<E> {
		// node contains type initialized as and next node
		E data;
		Node next;

		Node(E inp) {
			data = inp;
			next = null;
		}
	}

	// first node in list
	Node head;

	// if head.next is null, and head data is null, no pointer is next, so list is empty
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	// traverse list until index i
	// return null if does not exist
	@Override
	public E get(int i) {
		if (!isEmpty()) {
			// get first two nodes
			Node cur = head;
			Node next = head.next;

			// iterate until desired index at which to get
			for (int j = 0; j < i; j++) {
				cur = next;
				next = next.next;
			}
			return (E)cur.data;
		}
		// null if no value
		return null;
	}

	// add e at index i
	@Override
	public void add(int i, E e) {
		// node to add
		Node add = new Node(e);
		// if not empty, iterate through the list until i
		if (!isEmpty()) {
			Node cur = head;
			Node next = head.next;
			for (int j = 0; j < i-1; j++) {
				cur = cur.next;
				next = next.next;
			}

			// insert node add
			cur.next = add;
			add.next = next;
		} else {
			// if list is empty, make head node add
			head = add;
		}
	}

	// remove node at index i
	@Override
	public E remove(int i) {
		E data = null;
		if(!isEmpty()) {
			Node cur = head;
			Node next = head.next;
			for (int j = 0; j < i-1; j++) {
				cur = cur.next;
				next = next.next;
			}
			data = (E)next.data;
			cur.next = next.next;
		}
		return data;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	private class ListIterator  implements Iterator<E>
	{
		Node<E> cur;

		public ListIterator()
		{
			cur = head;
		}

		public boolean hasNext()
		{
			return cur != null;
		}

		public E next()
		{
			E res = cur.data;
			cur = cur.next;
			return res;
		}
	}

	// count nodes until end of list, return count
	@Override
	public int size() {
		Node cur = head;
		int count = 0;

		if (cur == null) {
			return 0;
		}

		while(cur != null) {
			cur = cur.next;
			count++;
		}

		return count;
	}	
	

	// make head of list head.next
	// return head
	@Override
	public E removeFirst() {
		E value = (E)head.data;
		head = head.next;
		return value;
	}

	// makes tail of list the second to last element
	@Override
	public E removeLast() {
		Node cur = head;
		Node prev = null;
		while(cur.next != null) {
			prev = cur;
			cur = cur.next;
		}
		prev.next = null;
		return (E)cur.data;
	}

	// adds node to head
	@Override
	public void addFirst(E e) {
		Node add = new Node(e);
		add.next = head;
		head = add;
	}

	// add node to tail
	@Override
	public void addLast(E e) {
		Node newNode = new Node<>(e);

		Node temp = head;

		if (isEmpty()) {
			head = newNode;
		} else {
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
		}
	}

	@Override
	public String toString() {
		String ret = "[";
		Node cur = head;
		while(cur.next != null) {
			ret += cur.data + ", ";
			cur = cur.next;
		}
		ret += cur.data + "]";
		return ret;
	}

	public E first() {
		if (isEmpty()) {
			return null;
		}

		return (E) head.data;
	}

	public E last() {
		if (isEmpty()) {
			return null;
		}

		SinglyLinkedList.Node temp = head;

		while (temp.next != null) {
			temp = temp.next;
		}

		return (E) temp.data;
	}
	
	public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());

		for (String s : sll) {
			System.out.print(s + ", ");
		}
	}


}
