package projectCode20280;

import java.util.LinkedList;
import java.util.List;

public class LinkedStack<E> implements Stack<E> {

    public static void main(String[] args) {
        LinkedStack<Integer> x = new LinkedStack<>();

		// check if new stack is empty, should be true
		System.out.println("Got: " + x.isEmpty() + ", expected: true");

		// push numbers 1-10 to stack
		for (int i = 0; i < 10; i++) {
			x.push(i);
		}
		// test size, pop, size after pop, top
		System.out.println("Size Got: " + x.size() + " Expected: 10");
		System.out.println("Pop Got: " + x.pop() + " Expected: 9");
		System.out.println("Size After Pop Got: " + x.size() + " Expected: 9");
		System.out.println("Top Got: " + x.top() + " Expected: 8");
    }

    // list to store data
    private LinkedList<E> list;

    // constructor
    public LinkedStack() {
		list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
       list.addFirst(e);
    }

    @Override
    public E top() {
        if (!isEmpty()) {
            // returns first element
            return list.get(0);
        }
        return null;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
        	return null;
		}
        return list.removeFirst();
    }

    @Override
    public String toString() {
        String ret = "[";
        for(E x : list) {
            ret += x + ", ";
        }

        ret = ret.substring(0, ret.length()-2);
        ret += "]";

        return ret;
    }

}
