package projectCode20280;

import java.util.Objects;

public class ArrayQueue<E> implements Queue<E> {

    private E data[];
    private int front;
    private int rear;
    private int capacity;
    private int size;

    // constructor
    public ArrayQueue(int s) {
        front = 0;
        rear = 0;
        size = 0;
        data = (E[]) new Object[s];
        capacity = s;

    }

    public static void main(String[] args) {
        // Test Suite

        // declare new queue of size 10
        ArrayQueue<Integer> x = new ArrayQueue<Integer>(10);

        // check if empty (should be)
        System.out.println("Expected: True, Got: " + x.isEmpty());

        // fill queue with 10-100 (not 1-10 so you make ure you dont return index)
        for (int i = 1; i <= 10; i++) {
            x.enqueue(i * 10);
        }

        // check if empty (should not be)
        System.out.println("Expected: False, Got: " + x.isEmpty() + "\n");

        // dequeue 5 times, each time printing the value that was taken out
        for (int i = 0; i < 5; i++) {
            System.out.println("First: " + x.first());
            System.out.println("Dequeue: " + x.dequeue() + "\n");
        }

        // check size (should be 5)
        System.out.println("Expected: 5, Got: " + x.size);

        // enqueue 60
        x.enqueue(110);

        // check size (should be 6)
        System.out.println("Expected: 6, Got: " + x.size);

        // dequeue 6 times, should work
        for (int i = 0; i < 6; i++) {
            System.out.println("First: " + x.first());
            System.out.println("Dequeue: " + x.dequeue() + "\n");
        }

        // dequeue again, should throw error
        try {
            System.out.println("Dequeue: " + x.dequeue() + "\n");
        } catch (Exception e) {
            System.out.println("Expected: Queue is empty, Underflow, Got: " + e.getMessage());
        }
    }

    @Override
    public int size() {
        // return dif from front and rear
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // check to see if another object can be added
        if (size == capacity) {
            throw new IllegalStateException("Queue is full, overflow");
        }

        size++;
        data[rear] = e;

        // % 10 so when its at the end it goes back to zero;
        rear = (rear + 1) % capacity;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return data[front];
    }

    @Override
    public E dequeue() {
        if (size == 0)
            throw new IllegalStateException("Queue is empty, Underflow");
        else {
            size--;
            E d = data[(front % capacity)];
            data[front] = null;
            front = (front + 1) % capacity;
            return d;
        }
    }

}
