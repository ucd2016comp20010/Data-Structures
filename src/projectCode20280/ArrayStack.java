package projectCode20280;

public class ArrayStack<E> implements Stack<E> {

    // array to store stack
    private E data[];

    // top of stack index
    private int t = -1;

    // capacity of stack
    public static final int CAPACITY = 1000;

    public static void main(String[] args) {
        ArrayStack<Integer> x = new ArrayStack<Integer>(10);

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

    // constructor
    @SuppressWarnings({"unchecked"})
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        // return index of top of stack + 1
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        // return if top of stack is -1
        return t == -1;
    }

    @Override
    public void push(E e) {
        if (t + 1 == data.length) {
            throw new IllegalArgumentException("Stack Full");
        }

        t++;
        data[t] = e;
    }

    @Override
    public E top() {
    	// if a value exists in stack, return it
        if (!isEmpty()) {
            return data[t];
        }
        // else return null
        return null;
    }

    @Override
    public E pop() {
    	// if empty, return null
        if (isEmpty()) {
            return null;
        }

        // else, save data, make index in data null, decrement top counter, return value
        E value = data[t];
        data[t] = null;
        t--;
        return value;
    }
}