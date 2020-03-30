package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<E> {
        // node contains type initialized as and next node
        E data;
        Node next;

        Node(E inp) {
            data = inp;
            next = head;
        }
    }

    Node head;


    @Override
    public int size() {
        if (head == null) return 0;
        Node cur = head;
        int count = 0;
        while (cur.next != head) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

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
            return (E) cur.data;
        }
        // null if no value
        return null;
    }

    @Override
    public void add(int i, E e) {
        // node to add
        Node add = new Node(e);
        // if not empty, iterate through the list until i
        if (!isEmpty()) {
            Node cur = head;
            Node next = head.next;
            for (int j = 0; j < i; j++) {
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

    @Override
    public E remove(int i) {
        E data = null;
        if (!isEmpty()) {
            Node cur = head;
            Node next = head.next;
            for (int j = 0; j < i; j++) {
                cur = cur.next;
                next = next.next;
            }
            data = (E) next.data;
            cur.next = next.next;
        }
        return data;
    }

    @Override
    public E removeFirst() {
        E value = (E) head.data;
        head = head.next;
        return value;
    }

    @Override
    public E removeLast() {
        Node cur = head;
        Node prev = null;
        while (cur.next != null) {
            prev = cur;
            cur = cur.next;
        }
        prev.next = head;
        return (E) cur.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        Node<E> cur;

        public ListIterator() {
            cur = head;
        }

        public boolean hasNext() {
            return cur != null;
        }

        public E next() {
            E res = cur.data;
            cur = cur.next;
            return res;
        }
    }

    @Override
    public void addFirst(E e) {
        Node add = new Node(e);
        add.next = head;
        head = add;
    }

    @Override
    public void addLast(E e) {
        if (!isEmpty()) {
            System.out.println("add");
            Node add = new Node(e);
            Node cur = head;
            int c = 0;
            while (cur != null) {
                cur = cur.next;
                c++;
                System.out.println(c);
            }
            cur.next = add;
            cur.next.next = head;
            // empty list
        } else {
            addFirst(e);
        }
    }

    public void rotate() {

    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; i++) {
            ll.addLast(i);
            System.out.println(i);

        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
