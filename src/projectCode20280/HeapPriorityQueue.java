
package projectCode20280;


import java.util.ArrayList;
import java.util.Comparator;


/**
 * An implementation of a priority queue using an array-based heap.
 */


public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    // make Heap Priority Queue
    ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */

    public HeapPriorityQueue() {
        super();
    }


    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */

    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }


    /**
     * Creates a priority queue initialized with the respective
     * key-value pairs.  The two arrays given will be paired
     * element-by-element. They are presumed to have the same
     * length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */

    public HeapPriorityQueue(K[] keys, V[] values) {
        super();

        if (keys.length < values.length) {
            for (int i = 0; i < keys.length; i++) {
                heap.add(new PQEntry<>(keys[i], values[i]));
            }
            heapify();
        } else {
            for (int i = 0; i < values.length; i++) {
                heap.add(new PQEntry<>(keys[i], values[i]));
            }
            heapify();
        }
    }

    // protected utilities
    protected int parent(int j) {
        return (j - 1) / 2;
    }

    protected int left(int j) {
        return 2 * j + 1;
    }

    protected int right(int j) {
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        if (left(j) < heap.size()) {
            return true;
        }
        return false;
    }

    protected boolean hasRight(int j) {
        if (right(j) < heap.size()) {
            return true;
        }
        return false;
    }


    /**
     * Exchanges the entries at indices i and j of the array list.
     */

    protected void swap(int i, int j) {
        // temp var for entry
        Entry<K, V> x = heap.get(i);

        // set temp location to new value
        heap.set(i, heap.get(j));

        // set new location to old value
        heap.set(j, x);
    }


// Moves the entry at index j higher, if necessary, to restore the heap property.

    protected void upheap(int j) {
        while (j > 0) {
            int par = parent(j);

            if (compare(heap.get(j), heap.get(par)) >= 0) {
                // reached end, break
                break;
            }

            // else

            // swap j and par
            swap(j, par);

            // set j to par
            j = par;
        }
    }


// Moves the entry at index j lower, if necessary, to restore the heap property.

    protected void downheap(int j) {
        while (hasLeft(j)) {
            int indexL = left(j);
            int childIndex = indexL;

            if (hasRight(j)) {
                int indexR = right(j);

                if (compare(heap.get(indexL), heap.get(indexR)) > 0) {
                    childIndex = indexR;
                }
            }

            if (compare(heap.get(childIndex), heap.get(j)) >= 0) {
                break;
            }

            swap(j, childIndex);
            j = childIndex;
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */

    protected void heapify() {
        // star at last index's parent, go until root
        int start = parent(size() - 1);

        // go until root index is reached
        for (int j = start; j >= 0; j--) {
            downheap(j);
        }
    }

    // public methods


    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */

    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */

    @Override
    public Entry<K, V> min() {
        if (heap.isEmpty() == true) {
            return null;
        }

        return heap.get(0);
    }


    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new entry
        Entry<K, V> newEntry = new PQEntry<>(key, value);

        // add entry
        heap.add(newEntry);

        // upheap
        upheap(heap.size() - 1);

        // return
        return newEntry;
    }


    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */

    @Override
    public Entry<K, V> removeMin() {
        // null if heap is empty
        if (heap.isEmpty()) {
            return null;
        }

        // x is temp to hold smallest entry
        Entry<K, V> x = heap.get(0);

        // swap first and last
        swap(0, heap.size() - 1);

        // remove last
        heap.remove(heap.size() - 1);

        // downheap
        downheap(0);

        // return
        return x;
    }


    /**
     * Used for debugging purposes only
     */

    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
                System.out.println("Invalid left child relationship");
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
                System.out.println("Invalid right child relationship");
        }
    }
}
